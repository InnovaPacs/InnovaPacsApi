package innova.pacs.api.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import innova.pacs.api.Application;

public class PdfUtil {
	private static Map<String, String> templates = new HashMap<>();
	private static final Object lock = new Object();
	
	public static final ByteArrayInputStream generatePdfFromStringTemplate(String stringTemplate, Rectangle pageSize) {
		Document document = new Document(pageSize);
		document.setMargins(45, 45, 50, 50);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter writer;
		try {
			writer = PdfWriter.getInstance(document, out);
			document.open();
			document.addAuthor("Innova Pacs");
			document.addCreator("Innova Pacs");
			document.addSubject("Diagnostic PDF");
			document.addCreationDate();
			document.addTitle("Diagnostic PDF");
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(stringTemplate));
			document.close();

		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
	
	/**
	 * Get HTML template 
	 * @param path: path of the template
	 * @param fileName: name of the template
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getInvoiceTemplate(String path, String fileName) {
		String template = templates.get(fileName);
		
		if (template == null) {
			try (InputStream in = Application.class.getClassLoader().getResourceAsStream(path + fileName.toLowerCase())) {
				template = IOUtils.toString(in);
				synchronized (lock) {
					templates.put(fileName, template);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return template;
	}
	
	/**
	 * Bytes From Byte Array InputStream
	 * @param bais
	 * @return
	 */
	public static byte[] readBytesFromByteArrayInputStream(ByteArrayInputStream bais) {
		byte[] array = new byte[bais.available()];
		try {
			bais.read(array);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return array;
	}
}
