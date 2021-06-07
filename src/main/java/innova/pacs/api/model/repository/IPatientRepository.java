package innova.pacs.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import innova.pacs.api.dto.PatientDto;
import innova.pacs.api.model.Patient;


public interface IPatientRepository extends PagingAndSortingRepository<Patient, Integer>{
	
	@Query(value = "SELECT patient.* FROM patient patient WHERE patient.birthdate is null", nativeQuery = true)
	public List<Patient> getByBirthdateOnNull();
	
	@Query(value = "SELECT NEW innova.pacs.api.dto.PatientDto ("
			+ " p.pk, "
			+ "	pi.patId, "
			+ "	pn.middleName, "
			+ "	pn.familyName, "
			+ "	pn.givenName, "
			+ " p.patSex ) "
			+ "	FROM Patient p "
			+ "	JOIN PatientId pi on p.patientIdFk = pi.pk "
			+ "	JOIN PersonName pn on p.patNameFk = pn.pk ")
	public List<PatientDto> getAllUsers();
	
	@Query(value = "SELECT NEW innova.pacs.api.dto.PatientDto ("
			+ " p.pk, "
			+ "	pi.patId, "
			+ "	pn.middleName, "
			+ "	pn.familyName, "
			+ "	pn.givenName, "
			+ " p.patSex ) "
			+ "	FROM Patient p "
			+ "	JOIN PatientId pi on p.patientIdFk = pi.pk "
			+ "	JOIN PersonName pn on p.patNameFk = pn.pk "
			+ "	WHERE p.pk = :id ")
	public PatientDto getById(@Param("id") Integer id);
}
