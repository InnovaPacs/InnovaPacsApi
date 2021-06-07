package innova.pacs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import innova.pacs.api.dto.StudyDto;
import innova.pacs.api.dto.StudyFullDto;
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

	@GetMapping("/full")
	public List<StudyFullDto> studyRepository() {
		return this.studyService.findFullStudies();
	}

	@GetMapping("/filter")
	public List<StudyFullDto> studyFilterRepository(@RequestParam(required = false) String name,
			@RequestParam(required = false) String institution,
			@RequestParam(required = false) String gender, @RequestParam(required = false) Integer instances,
			@RequestParam(required = false) String modality, @RequestParam(required = false) String patientId,
			@RequestParam(required = false) String studyDateEnd, @RequestParam(required = false) String studyDateInit, @RequestParam(required = false) String studyDescription) {

		return this.studyService.findFullStudiesFilter(name, institution, gender, instances, modality, patientId, studyDateEnd, studyDateInit, studyDescription);
	}
}
