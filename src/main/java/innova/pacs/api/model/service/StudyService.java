package innova.pacs.api.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innova.pacs.api.dto.StudyDto;
import innova.pacs.api.dto.StudyFullDto;
import innova.pacs.api.model.repository.IStudyRepository;
import innova.pacs.api.security.SecurityUtil;

@Service
public class StudyService {
	@Autowired
	private IStudyRepository studyRepository;
	
	public List<StudyDto> findByPatientFk(Integer patientFk){
		return this.studyRepository.findByPatientFk(patientFk);
	}
	
	public List<StudyFullDto> findFullStudies(){
		return this.studyRepository.findFullStudiesByUsername(SecurityUtil.getUsername());
	}
}
