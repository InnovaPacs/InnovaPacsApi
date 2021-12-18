package innova.pacs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import innova.pacs.api.model.DiagnosticPdfConfigiration;
import innova.pacs.api.model.service.DiagnosticPdfConfigirationService;

@RestController
@RequestMapping("/api/v1/diagnosisPdfConfiguration")
public class DiagnosticPdfConfigirationController {
	@Autowired
	private DiagnosticPdfConfigirationService diagnosticPdfConfigirationService;

	@GetMapping()
	public List<DiagnosticPdfConfigiration> getAllInstitutions() {
		return this.diagnosticPdfConfigirationService.findAll();
	}

	@PostMapping()
	public DiagnosticPdfConfigiration create(@RequestBody DiagnosticPdfConfigiration doctorProfile) {
		return this.diagnosticPdfConfigirationService.create(doctorProfile);
	}

	@PutMapping
	public DiagnosticPdfConfigiration update(@RequestBody DiagnosticPdfConfigiration doctorProfile) {
		return this.diagnosticPdfConfigirationService.update(doctorProfile);
	}

	@GetMapping("/{id}")
	public DiagnosticPdfConfigiration findById(@Param("id") Long id) {
		return this.diagnosticPdfConfigirationService.findById(id);
	}

	@GetMapping("/user/{id}")
	public List<DiagnosticPdfConfigiration> findByUserId(@Param("id") Long id) {
		return this.diagnosticPdfConfigirationService.findByUserId(id);
	}
}
