package innova.pacs.api.security;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import innova.pacs.api.model.service.EmailService;
import org.apache.commons.io.IOUtils;

public class SmtpUtil {
	private static Map<String, String> templates = new HashMap<>();
	private static final Object lock = new Object();
	
	public static String getTemplate(String fileName) {
		String template = templates.get(fileName);
		if (template == null) {
			try (InputStream in = EmailService.class
					.getResourceAsStream("/templates/" + fileName.toLowerCase())) {
				template = IOUtils.toString(in, Charset.defaultCharset());
				synchronized (lock) {
					templates.put(fileName, template);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return template;
	}
	
	public static String transformFromTemplate(Map<String, String> templates) {
		String template = SmtpUtil.getTemplate("basic.html");
		for (Map.Entry<String, String> metadata : templates.entrySet()) {
			template = template.replace("{{" + metadata.getKey() + "}}", metadata.getValue());
		}
		return template;
	}
}
