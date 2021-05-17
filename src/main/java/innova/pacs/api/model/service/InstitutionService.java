package innova.pacs.api.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innova.pacs.api.model.Institution;
import innova.pacs.api.model.InstitutionUser;
import innova.pacs.api.model.repository.IInstitutionUserRepository;
import innova.pacs.api.model.repository.IInstitutipnRepository;

@Service
public class InstitutionService {
	@Autowired
	private IInstitutipnRepository institutipnRepository;
	@Autowired
	private IInstitutionUserRepository institutionUserRepository;
	
	public List<Institution> findAll(){
		return (List<Institution>) this.institutipnRepository.findAll();
	}
	
	public InstitutionUser configurare(InstitutionUser institutionUser){
		return this.institutionUserRepository.save(institutionUser);
	}
}
