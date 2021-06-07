package innova.pacs.api.model.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import innova.pacs.api.dto.PatientDto;
import innova.pacs.api.model.Patient;
import innova.pacs.api.model.repository.IPatientRepository;

@Service
public class PatientService {
	@Autowired
	private IPatientRepository patientRepository;
	
	/**
	 * Find all patients
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<PatientDto> getAllUsers(){
		return this.patientRepository.getAllUsers();
	}
	
	/**
	 * Find all patients
	 * @return
	 */
	@Transactional(readOnly = true)
	public PatientDto getById(Integer id){
		return this.patientRepository.getById(id);
	}
	
	@Transactional
	public void refactorPatientBirthDate() {
		List<Patient> lstPatient = this.patientRepository.getByBirthdateOnNull();

		for (Patient patient : lstPatient) {
			if (patient.getPatBirthdate() != null) {
				try {

					Date tradeDate = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(patient.getPatBirthdate());
					patient.setBirthdate(tradeDate);
					
					this.patientRepository.save(patient);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
