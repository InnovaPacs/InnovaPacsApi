package innova.pacs.api.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innova.pacs.api.dto.ModalityDto;
import innova.pacs.api.model.repository.IStudyQueryAttrsRepository;

@Service
public class StudyQueryAttrsService {
	@Autowired
	private IStudyQueryAttrsRepository studyQueryAttrsRepository;
	
	public List<ModalityDto> getAllModalities(){
		return this.studyQueryAttrsRepository.getAllModalities();
	}
}
