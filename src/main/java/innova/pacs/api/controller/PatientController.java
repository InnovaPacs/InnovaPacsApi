package innova.pacs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import innova.pacs.api.dto.EmailConfigurationDto;
import innova.pacs.api.dto.PatientDto;
import innova.pacs.api.model.Patient;
import innova.pacs.api.model.service.PatientService;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	@GetMapping()
	public List<PatientDto> getAllPatients() {
		return this.patientService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public PatientDto getById(@PathVariable("id") Integer id) {
		return this.patientService.getById(id);
	}
	
	@PutMapping
	public Patient confireEmail(@RequestBody EmailConfigurationDto emailConfigurationDto) throws Exception {
		return this.patientService.configureEmail(emailConfigurationDto);
	}
}
