package innova.pacs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import innova.pacs.api.dto.ModalityDto;
import innova.pacs.api.model.service.StudyQueryAttrsService;

@RestController
@RequestMapping("/api/v1/attrs")
public class StudyQueryAttrsController {
	@Autowired
	private StudyQueryAttrsService studyQueryAttrsService;
	
	@GetMapping("/modalities")
	public List<ModalityDto> getAllModalities() {
		return studyQueryAttrsService.getAllModalities();
	}
}
