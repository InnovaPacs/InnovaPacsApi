package innova.pacs.api.model.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.text.PageSize;

import innova.pacs.api.dto.InnovaDiagnosticPdfDto;
import innova.pacs.api.model.InnovaFile;
import innova.pacs.api.util.PdfUtil;

@Service
@Qualifier("innovaFileDiagnosticService")
public class InnovaFileDiagnosticService implements IInnovaFileService {
	@Autowired
	private InnovaFileService innovaFileService;
	@Value("${innova.pacs.file.diafnostic.template.path}")
	private String templatePath;
	@Value("${innova.pacs.file.diafnostic.template.name}")
	private String templateName;
	@Value("${innova.tmp.path}")
	private String tmpPath;
	private String logo;

	@Override
	public String setData(String template, Object dto) throws IOException {
		InnovaDiagnosticPdfDto innovaDiagnosticPdf = (InnovaDiagnosticPdfDto) dto;
		Date today = new Date();
		
		template = template.replace("@@color@@", innovaDiagnosticPdf.getHeaderColor());
		template = template.replace("@@patientName@@", innovaDiagnosticPdf.getPatientName());
		template = template.replace("@@diagnosticDate@@", innovaDiagnosticPdf.getDiagnosticDate());
		template = template.replace("@@patientBirth@@", innovaDiagnosticPdf.getPatientBirth());
		template = template.replace("@@patientSex@@", innovaDiagnosticPdf.getPatientSex());
		template = template.replace("@@patientAge@@", innovaDiagnosticPdf.getPatientAge());
		template = template.replace("@@diagnostic@@", innovaDiagnosticPdf.getDiagnostic());
		template = template.replace("@@docatorName@@", innovaDiagnosticPdf.getDoctorName());
		template = template.replace("@@doctorTitle@@", innovaDiagnosticPdf.getDoctorTitle());
		template = template.replace("@@doctorLicense@@", innovaDiagnosticPdf.getDoctorLicense());
		template = template.replace("@@doctorUniversity@@", innovaDiagnosticPdf.getDoctorUniversity());
		
		
		if(innovaDiagnosticPdf.getLogo() != null) {
			this.logo = String.format("%s_%s", today.getTime(), innovaDiagnosticPdf.getLogo().getName());
			InputStream inputStream = new ByteArrayInputStream(innovaDiagnosticPdf.getLogo().getBin());

	        File file = new File(String.format("%s/%s", this.tmpPath, this.logo));
	        Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
	        
			template = template.replace("@@logoPath@@", String.format("%s/%s", this.tmpPath, this.logo));
		}
		
		return template;
	}

	@Override
	public ByteArrayInputStream preview(Object dto) throws IOException {
		InnovaDiagnosticPdfDto innovaDiagnosticPdf = (InnovaDiagnosticPdfDto) dto;

		String template = PdfUtil.getInvoiceTemplate(this.templatePath, this.templateName);
		template = this.setData(template, innovaDiagnosticPdf);

		ByteArrayInputStream diagnosticFile = PdfUtil.generatePdfFromStringTemplate(template, PageSize.A4);

		Files.delete(Paths.get(String.format("%s/%s", this.tmpPath, this.logo)));
		
		return diagnosticFile;
	}

	@Override
	public ByteArrayInputStream testService() throws IOException {
		InnovaDiagnosticPdfDto innovaDiagnosticPdf = new InnovaDiagnosticPdfDto();
		InnovaFile file = this.innovaFileService.findById(8L);

		innovaDiagnosticPdf.setPatientAge("31");
		innovaDiagnosticPdf.setPatientBirth("02/09/1990");
		innovaDiagnosticPdf.setDiagnostic(
				"Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.");
		innovaDiagnosticPdf.setDiagnosticDate("11/12/2021");
		innovaDiagnosticPdf.setDoctorName("Mariano");
		innovaDiagnosticPdf.setDoctorTitle("Dr.");
		innovaDiagnosticPdf.setHeaderColor("#5b9aa0");
		innovaDiagnosticPdf.setLogo(file);
		innovaDiagnosticPdf.setPatientName("Jose Luis Campos Bautista");
		innovaDiagnosticPdf.setDoctorLicense("0000000");
		innovaDiagnosticPdf.setPatientSex("Hombre");
		innovaDiagnosticPdf.setDoctorUniversity("universidad auntononoma del estado de hidalgo");

		return this.preview(innovaDiagnosticPdf);
	}

}
