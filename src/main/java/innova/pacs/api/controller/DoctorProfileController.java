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

import innova.pacs.api.model.DoctorProfile;
import innova.pacs.api.model.service.DoctorProfileService;

@RestController
@RequestMapping("/api/v1/doctorProfile")
public class DoctorProfileController {

	@Autowired
	private DoctorProfileService doctorProfileService;

	@GetMapping()
	public List<DoctorProfile> getAllInstitutions() {
		return this.doctorProfileService.findAll();
	}

	@PostMapping()
	public DoctorProfile create(@RequestBody DoctorProfile doctorProfile) {
		return this.doctorProfileService.create(doctorProfile);
	}

	@PutMapping
	public DoctorProfile update(@RequestBody DoctorProfile doctorProfile) {
		return this.doctorProfileService.update(doctorProfile);
	}

	@GetMapping("/{id}")
	public DoctorProfile findById(@Param("id") Long id) {
		return this.doctorProfileService.findById(id);
	}

	@GetMapping("/user")
	public DoctorProfile findByUserId() {
		return this.doctorProfileService.findByUserId();
	}
}
