package innova.pacs.api.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innova.pacs.api.dto.PatientDto;
import innova.pacs.api.model.repository.IPatientRepository;

@Service
public class PatientService {
	@Autowired
	private IPatientRepository patientRepository;
	
	/**
	 * Find all patients
	 * @return
	 */
	public List<PatientDto> getAllUsers(){
		return this.patientRepository.getAllUsers();
	}
	
	/**
	 * Find all patients
	 * @return
	 */
	public PatientDto getById(Integer id){
		return this.patientRepository.getById(id);
	}
}
