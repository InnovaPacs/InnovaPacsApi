package innova.pacs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import innova.pacs.api.dto.StudyDto;
import innova.pacs.api.model.service.StudyService;

@RestController
@RequestMapping("/api/v1/studies")
public class StudyController {
	@Autowired
	private StudyService studyService;
	
	@GetMapping("/patients/{patientFk}")
	public List<StudyDto> getStudiesByPatientFk(@PathVariable Integer patientFk) {
		return this.studyService.findByPatientFk(patientFk);
	}
}
