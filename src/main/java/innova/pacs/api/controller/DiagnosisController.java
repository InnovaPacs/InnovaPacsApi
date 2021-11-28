package innova.pacs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import innova.pacs.api.dto.DiagnosisDto;
import innova.pacs.api.model.Diagnosis;
import innova.pacs.api.model.service.DiagnosisService;

@RestController
@RequestMapping("/api/v1/diagnoses")
public class DiagnosisController {
	@Autowired
	private DiagnosisService diagnosisService;
	
	@GetMapping("/study/{id}")
	public List<DiagnosisDto> getAllByStudyId(@PathVariable("id") Long id) {
		return this.diagnosisService.findByStudyId(id);
	}
	
	@PostMapping()
	public Diagnosis create(@RequestBody Diagnosis diagnosis) {
		return this.diagnosisService.create(diagnosis);
	}
	
	@PutMapping
	public Diagnosis update(@RequestBody Diagnosis diagnosis) {
		return this.diagnosisService.update(diagnosis);
	}
	
	@GetMapping("/{id}")
	public Diagnosis findById(@PathVariable("id") Long id) {
		return this.diagnosisService.findById(id);
	}
}
