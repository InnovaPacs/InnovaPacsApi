package innova.pacs.api.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
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
	public static final String SIGNATURE_DATE_PATTERN = "yyyy-MM-dd";
	public static final String BIRTHDATE_DATE_PATTERN = "yyyy-MM-dd";
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
	
	public static String formatSignatureDate(Date date) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(SIGNATURE_DATE_PATTERN);
			return dateFormat.format(date);
		} catch (Exception e) {
		}

		return "";
	}
	
	public static String formatBirthDate(Date date) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(BIRTHDATE_DATE_PATTERN);
			return dateFormat.format(date);
		} catch (Exception e) {
		}

		return "";
	}
	
	/**
	 * Convert date to LocalDate
	 * @param date
	 */
	public static LocalDate convertDateToLocalDate (Date date) {
		
		if(date == null) {
			return LocalDate.now();
		}
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = date.toInstant();
		LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
		
		return localDate;
	}
	
	public static Date convertLocalDateToDate (LocalDate date) {
		return java.util.Date.from(date.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
	}
	
	/**
	 * Get age form birth date
	 * @param startDate
	 * @return
	 */
	public static Integer getAgeFromBirthDate (LocalDate startDate) {
		Period period = Period.between(startDate, LocalDate.now());
		return period.getYears();
	}
}
