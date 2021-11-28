package innova.pacs.api.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innova.pacs.api.dto.DiagnosisDto;
import innova.pacs.api.model.Diagnosis;
import innova.pacs.api.model.User;
import innova.pacs.api.model.repository.IDiagnosisRepository;

@Service
public class DiagnosisService {
	@Autowired
	private IDiagnosisRepository diagnosisRepository;
	@Autowired
	private UserService userService;
	
	/**
	 * Save file
	 * @param diagnosis
	 * @return
	 */
	public Diagnosis create(Diagnosis diagnosis) {
		User user = this.userService.getUserInsession();
		diagnosis.setCreatorId(user.getId());
		
		return this.diagnosisRepository.save(diagnosis);
	}
	
	/**
	 * Update file
	 * @param innovaFile
	 * @return
	 */
	public Diagnosis update(Diagnosis diagnosis) {
		return this.diagnosisRepository.save(diagnosis);
	}
	
	/**
	 * Find by id
	 * @param id
	 * @return
	 */
	public Diagnosis findById(Long id) {
		Optional<Diagnosis> optFile = this.diagnosisRepository.findById(id);
		return optFile.get();
	}
	
	/**
	 * Find by study id
	 * @param studyId
	 * @return
	 */
	public List<DiagnosisDto> findByStudyId(Long studyId) {
		return this.diagnosisRepository.getByStudyId(studyId);
	}
	
}
