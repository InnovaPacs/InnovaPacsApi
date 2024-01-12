package innova.pacs.api.client;

import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import innova.pacs.api.dto.AETDto;

@SuppressWarnings("deprecation")
@Service
public class Dcm4cheeClient {
	@Value("${dcm4chee.host}")
	private String host;
	@Value("${dcm4chee.port}")
	private String port;
	@Value("${dcm4chee.api.port}")
	private String apiPort;
	@Value("${dcm4chee.client}")
	private String client;
	@Value("${dcm4chee.id}")
	private String id;
	@Value("${dcm4chee.secret}")
	private String secret;
	@Value("${dcm4chee.ae.title}")
	private String aeTitle;

	/**
	 * Get custom client
	 * 
	 * @return <HttpClient>
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 */
	private HttpClient getClient() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		HttpClientBuilder b = HttpClientBuilder.create();

		// setup a Trust Strategy that allows all certificates.
		//
		SSLContext sslContext = null;

		sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			public boolean isTrusted(final X509Certificate[] arg0, final String arg1) throws CertificateException {
				return true;
			}
		}).build();

		b.setSslcontext(sslContext);

		// don't check Hostnames, either.
		// -- use SSLConnectionSocketFactory.getDefaultHostnameVerifier(), if you don't
		// want to weaken
		HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

		// here's the special part:
		// -- need to create an SSL Socket Factory, to use our weakened "trust
		// strategy";
		// -- and create a Registry, to register it.
		//
		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslSocketFactory)
				.build();

		// now, we create connection-manager using our Registry.
		// -- allows multi-threaded use
		PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		b.setConnectionManager(connMgr);

		// finally, build the HttpClient;
		// -- done!
		return b.build();
	}

	/**
	 * Authentication to DCM4CHEE
	 * 
	 * @return <LinkedHashMap>
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws UnsupportedEncodingException
	 */
	public JSONObject auth() throws Exception {
		String url = String.format("https://%s:%s/auth/realms/dcm4che/protocol/openid-connect/token", this.host,
				this.port);

		HttpPost post = new HttpPost(url);

		HttpClient httpClient = this.getClient();

		List<NameValuePair> urlParameters = new ArrayList<>();
		urlParameters.add(new BasicNameValuePair("grant_type", this.client));
		urlParameters.add(new BasicNameValuePair("client_id", this.id));
		urlParameters.add(new BasicNameValuePair("client_secret", this.secret));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = httpClient.execute(post);

		HttpEntity entity = response.getEntity();

		return new JSONObject(EntityUtils.toString(entity));
	}

	public void updateStudies() {
		String updateUrl = String.format("http://%s:%s/dcm4chee-arc/aets/%s/rs/studies", this.host, this.apiPort, this.aeTitle);
		HttpGet updaterequest = new HttpGet(updateUrl);
		HttpClient httpClient;
		try {
			
			httpClient = this.getClient();
			httpClient.execute(updaterequest);
			HttpResponse updteResponse = httpClient.execute(updaterequest);
			
			int status = updteResponse.getStatusLine().getStatusCode();
			
			System.out.println("#### Update status: "+status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get all AE Titles
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<AETDto> getAes() throws Exception {
		String url = String.format("http://%s:%s/dcm4chee-arc/aes", this.host, this.apiPort);
		HttpGet request = new HttpGet(url);
		HttpClient httpClient = this.getClient();

//		JSONObject auth = this.auth();
//		String token = auth.getString("access_token");

//		request.addHeader(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token));

		HttpResponse response = httpClient.execute(request);
		
		int statusCode = response.getStatusLine().getStatusCode();
		List<AETDto> lstAet = new ArrayList<AETDto>();

		if (statusCode == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();

			JSONArray aeTitleArray = new JSONArray(EntityUtils.toString(entity));

			for (int i = 0; i < aeTitleArray.length(); i++) {
				JSONObject objects = aeTitleArray.optJSONObject(i);
				AETDto aet = new AETDto();
				aet.setName(objects.getString("dicomAETitle"));
				lstAet.add(aet);
			}
		}

		return lstAet;
	}

	/**
	 * Export study
	 * 
	 * @throws Exception
	 */
	public void export(String uuid, String aets) throws Exception {
//		https://192.168.3.108:8443/dcm4chee-arc/aets/DCM4CHEE/rs/studies/1.2.392.200036.9125.2.20839136157173252.64951091883.41568/export/dicom:DCM4CHEE?
		String url = String.format("http://%s:%s/dcm4chee-arc/aets/%s/rs/studies/%s/export/dicom:%s?", this.host,
				this.apiPort, this.aeTitle, uuid, aets);

		System.out.println("Url: " + url);
		HttpPost request = new HttpPost(url);
		HttpClient httpClient = this.getClient();
//
//		JSONObject auth = this.auth();
//		String token = auth.getString("access_token");
//		System.out.println("Token: " + token);

//		request.addHeader(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token));

		HttpResponse response = httpClient.execute(request);

		HttpEntity entity = response.getEntity();

		JSONObject respomse = new JSONObject(EntityUtils.toString(entity));

		System.out.println("Response: " + respomse.toString());
	}
}
