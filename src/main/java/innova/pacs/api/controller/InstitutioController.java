package innova.pacs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import innova.pacs.api.model.Institution;
import innova.pacs.api.model.InstitutionUser;
import innova.pacs.api.model.service.InstitutionService;

@RestController
@RequestMapping("/api/v1/institutions")
public class InstitutioController {
	@Autowired
	private InstitutionService institutionService; 
	
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
}
