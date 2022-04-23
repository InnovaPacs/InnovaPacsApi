package innova.pacs.api.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import innova.pacs.api.dto.InstitutionConfigurtionDto;
import innova.pacs.api.model.Institution;
import innova.pacs.api.model.InstitutionUser;
import innova.pacs.api.model.service.InstitutionService;
import innova.pacs.api.model.service.ReportService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/institutions")
public class InstitutioController {
	@Autowired
	private InstitutionService institutionService;
	@Autowired
	private ReportService reportService;

	@GetMapping()
	public List<Institution> getAllInstitutions() {
		return institutionService.findAll();
	}

	@GetMapping("/{id}")
	public Institution gteById(@PathVariable("id") Integer id) {
		return institutionService.findById(id);
	}

	@PostMapping("/configuration")
	public InstitutionUser getAllInstitutions(@RequestBody InstitutionUser institutionUser) {
		return institutionService.configure(institutionUser);
	}

	@GetMapping("/report/{institutionId}")
	public void generateReportByInctirutionId(@PathVariable Integer institutionId, HttpServletResponse response)
			throws IOException {

		File file = this.reportService.generateInstitutionReport();
//			File file = new File("/home/bautistaj/Documentos/dev/innovapacs/reports/example.pdf");

		byte[] byteArrayFile = Files.readAllBytes(Paths.get(file.getAbsolutePath()));

		MediaType mediaType = MediaType.APPLICATION_PDF;
		response.setContentType(mediaType.toString());
		response.setHeader("Content-Disposition", "inline; filename=intitutionReport.pdf\"");
		response.setContentLengthLong(byteArrayFile.length);
		response.getOutputStream().write(byteArrayFile);
	}
	
	@GetMapping("/user/{userId}/configuration")
	public List<InstitutionConfigurtionDto> getConfiguration(@PathVariable("userId") Long userId) {
		return this.institutionService.getConfiguration(userId);
	}
	
	@PostMapping("/user/{userId}")
	public List<InstitutionConfigurtionDto> saveConfiguration(@PathVariable("userId") Long userId,
			@RequestBody List<Long> lstIds) {
		return this.institutionService.saveConfiguration(userId, lstIds);
	}
}
