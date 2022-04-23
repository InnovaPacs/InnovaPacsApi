package innova.pacs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import innova.pacs.api.model.DiagnosticPdfConfigiration;
import innova.pacs.api.model.User;
import innova.pacs.api.model.service.DiagnosticPdfConfigirationService;
import innova.pacs.api.model.service.UserService;
import innova.pacs.api.security.SecurityUtil;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/diagnosisPdfConfiguration")
public class DiagnosticPdfConfigirationController {
	@Autowired
	private DiagnosticPdfConfigirationService diagnosticPdfConfigirationService;
	@Autowired
	private UserService userService;
	
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

	@GetMapping("/all/user/{id}")
	public List<DiagnosticPdfConfigiration> findByUserId(@Param("id") Long id) {
		return this.diagnosticPdfConfigirationService.findByUserId(id);
	}
	
	@GetMapping("/user")
	public DiagnosticPdfConfigiration findSingleByUserId() {
		String username = SecurityUtil.getUsername();
		User user = this.userService.findByUsername(username);
		
		List<DiagnosticPdfConfigiration> lst = this.diagnosticPdfConfigirationService.findByUserId(user.getId());
		
		if(lst != null && !lst.isEmpty()) {
			return lst.get(0);
		}
		
		return null;
	}
}
