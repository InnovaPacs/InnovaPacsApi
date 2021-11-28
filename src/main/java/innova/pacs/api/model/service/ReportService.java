package innova.pacs.api.model.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

@SuppressWarnings("deprecation")
@Service
public class ReportService {

	/**
	 * Generate report by intitution id
	 */
	public File generateInstitutionReport() {
		try {
			Document document = new Document(PageSize.LETTER);

			PdfWriter.getInstance(document,
					new FileOutputStream("/home/bautistaj/Documentos/dev/innovapacs/reports/example.pdf"));
			
			document.open();
			document.addAuthor("Innova Pacs");
			document.addCreator("Innova Pacs");
			document.addSubject("Institution Report");
			document.addCreationDate();
			document.addTitle("Institution Report");

			HTMLWorker htmlWorker = new HTMLWorker(document);
			String str = "<!DOCTYPE html>\n"
					+ "<html lang=\"en\">\n"
					+ "<head>\n"
					+ "    <meta charset=\"UTF-8\">\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
					+ "    <style>\n"
					+ "        body{\n"
					+ "            width:700px;\n"
					+ "            margin:0 auto;\n"
					+ "            background-color:#fff;\n"
					+ "            border:5px solid #fafafa;\n"
					+ "            padding:20px;\n"
					+ "            font-family: Heebo;\n"
					+ "            font-size: 15px;\n"
					+ "            font-weight: 300;\n"
					+ "            text-align: center;\n"
					+ "            border-collapse: separate;\n"
					+ "            border-spacing: 0;\n"
					+ "        }\n"
					+ "           \n"
					+ "        table {\n"
					+ "          color: #67636A;\n"
					+ "          font-family: Heebo;\n"
					+ "          font-size: 15px;\n"
					+ "          font-weight: 300;\n"
					+ "          text-align: center;\n"
					+ "          border-collapse: separate;\n"
					+ "          border-spacing: 0;\n"
					+ "          width: 99%;\n"
					+ "          margin: 10px auto;\n"
					+ "          box-shadow:none;\n"
					+ "        }\n"
					+ "        </style>\n"
					+ "</head>\n"
					+ "<body style=\"padding: 10px;\">\n"
					+ "    <table style=\"width: 100%;\">\n"
					+ "        <tr>\n"
					+ "            <td>\n"
					+ "                <img src=\"http://localhost:4200/assets/images/innovapacs.png\" alt=\"\"/>\n"
					+ "                <img src=\"http://localhost:4200/assets/images/innovapacstext.png\" alt=\"\"/>\n"
					+ "            </td>\n"
					+ "            <td><h2>IMAGE Information Systems Ltd</h2></td>\n"
					+ "        </tr>\n"
					+ "    </table>\n"
					+ "    \n"
					+ "    <table style=\"width: 100%; margin-top: 10px; margin-bottom: 10px;\">\n"
					+ "        <thead style=\"background-color: #f8f8f8;\">\n"
					+ "            <tr>\n"
					+ "                <td><h2 style=\"padding-left: 10px;\">Resumen de estudios</h2></td>\n"
					+ "                <td><h2 style=\"padding-left: 10px;\">Corte al 29/08/2021</h2></td>\n"
					+ "            </tr>\n"
					+ "        </thead>\n"
					+ "    </table>\n"
					+ "    \n"
					+ "\n"
					+ "    <table style=\"width: 100%;\">\n"
					+ "        <thead style=\"background-color: #f8f8f8;\">\n"
					+ "            <tr>\n"
					+ "                <td> <h3 style=\"padding-left: 10px;\">Pacientes: 1</h3></td>\n"
					+ "                <td> <h3 style=\"padding-left: 10px;\">Estudios: 1</h3></td>\n"
					+ "                <td> <h3 style=\"padding-left: 10px;\">Imágenes: 1</h3></td>\n"
					+ "            </tr>\n"
					+ "        </thead>\n"
					+ "    </table>\n"
					+ "\n"
					+ "    <h2>Reporte por modalidad</h2>\n"
					+ "    <table style=\"width: 100%;\">\n"
					+ "        <thead style=\"background-color: #3498bd;\">\n"
					+ "            <tr>\n"
					+ "                <th style=\"font-weight: bold; color: #FFFFFF; padding-top: 5px; padding-bottom: 5px;\">Clave</th>\n"
					+ "                <th style=\"font-weight: bold; color: #FFFFFF; padding-top: 5px; padding-bottom: 5px;\">Modalidad</th>\n"
					+ "                <th style=\"font-weight: bold; color: #FFFFFF; padding-top: 5px; padding-bottom: 5px;\">Concepto</th>\n"
					+ "                <th style=\"font-weight: bold; color: #FFFFFF; padding-top: 5px; padding-bottom: 5px;\">Cantidad</th>\n"
					+ "            </tr>\n"
					+ "          </thead>\n"
					+ "          <tbody style=\"background-color: #f8f8f8;\">\n"
					+ "                <tr>\n"
					+ "                  <th scope=\"row\">CT</th>\n"
					+ "                  <th scope=\"row\">Tomografias</th>\n"
					+ "                  <th scope=\"row\">Estudios</th>\n"
					+ "                  <th scope=\"row\">1</th>\n"
					+ "                </tr>\n"
					+ "                <tr>\n"
					+ "                    <th scope=\"row\"></th>\n"
					+ "                    <th scope=\"row\"></th>\n"
					+ "                    <th scope=\"row\">Pacientes</th>\n"
					+ "                    <th scope=\"row\">1</th>\n"
					+ "                </tr>\n"
					+ "                <tr>\n"
					+ "                    <th scope=\"row\"></th>\n"
					+ "                    <th scope=\"row\"></th>\n"
					+ "                    <th scope=\"row\">Imágenes</th>\n"
					+ "                    <th scope=\"row\">1</th>\n"
					+ "                </tr>\n"
					+ "                <tr>\n"
					+ "                    <th scope=\"row\">MR</th>\n"
					+ "                    <th scope=\"row\">Resononacias</th>\n"
					+ "                    <th scope=\"row\">Estudios</th>\n"
					+ "                    <th scope=\"row\">1</th>\n"
					+ "                </tr>\n"
					+ "                <tr>\n"
					+ "                    <th scope=\"row\"></th>\n"
					+ "                    <th scope=\"row\"></th>\n"
					+ "                    <th scope=\"row\">Pacientes</th>\n"
					+ "                    <th scope=\"row\">1</th>\n"
					+ "                </tr>\n"
					+ "                <tr>\n"
					+ "                    <th scope=\"row\"></th>\n"
					+ "                    <th scope=\"row\"></th>\n"
					+ "                    <th scope=\"row\">Imágenes</th>\n"
					+ "                    <th scope=\"row\">1</th>\n"
					+ "                </tr>\n"
					+ "          </tbody>\n"
					+ "    </table>\n"
					+ "</body>\n"
					+ "</html>";
			htmlWorker.parse(new StringReader(str));
			document.close();

			System.out.println("Done");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new File("/home/bautistaj/Documentos/dev/innovapacs/reports/example.pdf");
	}

}
