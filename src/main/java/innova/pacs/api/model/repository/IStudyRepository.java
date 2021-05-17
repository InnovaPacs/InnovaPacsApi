package innova.pacs.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import innova.pacs.api.dto.StudyDto;
import innova.pacs.api.dto.StudyFullDto;
import innova.pacs.api.model.Study;

public interface IStudyRepository extends PagingAndSortingRepository<Study, Integer>{
	@Query(value = "SELECT distinct new innova.pacs.api.dto.StudyDto( "
			+ "study.pk, "
			+ "study.createdTime, "
			+ "study.studyDate, "
			+ "study.studyId, "
			+ "study.studyIuid,"
			+ "study.patientFk,"
			+ "study.studyDesc, "
			+ "series.modality ) "
			+ " FROM "
			+ "	Study study "
			+ "	JOIN Series series ON study.pk = series.studyFk"
			+ " WHERE study.patientFk = :patientFk ")
	public List<StudyDto> findByPatientFk(@Param("patientFk") Integer patientFk);

	@Query(value = "SELECT distinct new innova.pacs.api.dto.StudyFullDto(" 
			+ "patient.pk, " 
			+ "patientId.patId, "
			+ "personName.middleName, " 
			+ "personName.familyName, " 
			+ "personName.givenName, " 
			+ "patient.patSex, "
			+ "study.pk, " 
			+ "study.createdTime, " 
			+ "study.studyDate, " 
			+ "study.studyId, " 
			+ "study.studyIuid, "
			+ "study.studyDesc, " 
			+ "studyQueryAttrs.modsInStudy, "
			+ "studyQueryAttrs.numInstances, "
			+ "patient.patBirthdate )" 
			+ " FROM Patient patient "
			+ " JOIN PatientId patientId ON patient.patientIdFk =  patientId.pk "
			+ " JOIN PersonName personName ON patient.patNameFk = personName.pk "
			+ " JOIN Study study ON study.patientFk = patientId.pk "
			+ " JOIN StudyQueryAttrs studyQueryAttrs ON study.pk = studyQueryAttrs.studyFk")
	public List<StudyFullDto> findFullStudies();
}
