package innova.pacs.api.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innova.pacs.api.model.DoctorProfile;
import innova.pacs.api.model.User;
import innova.pacs.api.model.repository.IDoctorProfileRepository;
import innova.pacs.api.security.SecurityUtil;

@Service
public class DoctorProfileService {
	@Autowired
	private IDoctorProfileRepository doctorProfileRepository;
	@Autowired
	private UserService userService;

	/**
	 * Save
	 * 
	 * @param innovaFile
	 * @return
	 */
	public DoctorProfile create(DoctorProfile doctorProfile) {
		String username = SecurityUtil.getUsername();
		User user = this.userService.findByUsername(username);
		doctorProfile.setUserId(user.getId());
		
		return doctorProfileRepository.save(doctorProfile);
	}

	/**
	 * Update file
	 * 
	 * @param innovaFile
	 * @return
	 */
	public DoctorProfile update(DoctorProfile doctorProfile) {
		return this.doctorProfileRepository.save(doctorProfile);
	}

	/**
	 * Find by id
	 * 
	 * @param id
	 * @return
	 */
	public DoctorProfile findById(Long id) {
		Optional<DoctorProfile> optFile = this.doctorProfileRepository.findById(id);
		return optFile.get();
	}

	/**
	 * Find by study id
	 * 
	 * @param studyId
	 * @return
	 */
	public List<DoctorProfile> findAll() {
		return (List<DoctorProfile>) this.doctorProfileRepository.findAll();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public DoctorProfile findByUserId() {
		String username = SecurityUtil.getUsername();
		User user = this.userService.findByUsername(username);
		return this.doctorProfileRepository.findByUserId(user.getId());
	}
}
