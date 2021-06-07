package innova.pacs.api.model.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import innova.pacs.api.dto.StudyDto;
import innova.pacs.api.dto.StudyFullDto;
import innova.pacs.api.model.Study;

public interface IStudyRepository extends PagingAndSortingRepository<Study, Integer> {

	@Query(value = "SELECT study.* FROM study study WHERE study.date is null", nativeQuery = true)
	public List<Study> getBySrudyDateOnNull();

	@Query(value = "SELECT distinct new innova.pacs.api.dto.StudyDto( " + "study.pk, " + "study.createdTime, "
			+ "study.studyDate, " + "study.studyId, " + "study.studyIuid," + "study.patientFk," + "study.studyDesc, "
			+ "series.modality," + "series.institution ) " + " FROM " + "	Study study "
			+ "	JOIN Series series ON study.pk = series.studyFk" + " WHERE study.patientFk = :patientFk ")
	public List<StudyDto> findByPatientFk(@Param("patientFk") Integer patientFk);

	@Query(value = "SELECT distinct new innova.pacs.api.dto.StudyFullDto(" + "patient.pk, " + "patientId.patId, "
			+ "personName.middleName, " + "personName.familyName, " + "personName.givenName, " + "patient.patSex, "
			+ "study.pk, " + "study.createdTime, " + "study.date, " + "study.studyId, " + "study.studyIuid, "
			+ "study.studyDesc, " + "studyQueryAttrs.modsInStudy, " + "studyQueryAttrs.numInstances, "
			+ "patient.birthdate, " + "series.institution )" + " FROM Patient patient "
			+ " JOIN PatientId patientId ON patient.patientIdFk =  patientId.pk "
			+ " JOIN PersonName personName ON patient.patNameFk = personName.pk "
			+ " JOIN Study study ON study.patientFk = patientId.pk "
			+ " JOIN StudyQueryAttrs studyQueryAttrs ON study.pk = studyQueryAttrs.studyFk "
			+ " JOIN Series series ON study.pk = series.studyFk "
			+ " JOIN Institution institution ON series.institution = institution.name "
			+ "	JOIN InstitutionUser iuser ON institution.id = iuser.institutionId "
			+ "	JOIN User suser ON iuser.userId = suser.id " + "	where suser.username = :username ")
	public List<StudyFullDto> findFullStudiesByUsername(@Param("username") String username);

	/**
	 * Filter studies
	 * 
	 * @param username
	 * @param institution
	 * @param patientId
	 * @param patientSex
	 * @param studyDesc
	 * @param modsInStudy
	 * @return
	 */
	@Query(value = "SELECT distinct new innova.pacs.api.dto.StudyFullDto(" 
			+ "patient.pk, " + "patientId.patId, "
			+ "personName.middleName, " 
			+ "personName.familyName, " 
			+ "personName.givenName, " 
			+ "patient.patSex, "
			+ "study.pk, " 
			+ "study.createdTime, " 
			+ "study.date, " 
			+ "study.studyId, " 
			+ "study.studyIuid, "
			+ "study.studyDesc, " 
			+ "studyQueryAttrs.modsInStudy, " 
			+ "studyQueryAttrs.numInstances, "
			+ "patient.birthdate, " 
			+ "series.institution )" 
			+ " FROM Patient patient "
			+ " JOIN PatientId patientId ON patient.patientIdFk =  patientId.pk "
			+ " JOIN PersonName personName ON patient.patNameFk = personName.pk "
			+ " JOIN Study study ON study.patientFk = patientId.pk "
			+ " JOIN StudyQueryAttrs studyQueryAttrs ON study.pk = studyQueryAttrs.studyFk "
			+ " JOIN Series series ON study.pk = series.studyFk "
			+ " JOIN Institution institution ON series.institution = institution.name "
			+ "	JOIN InstitutionUser iuser ON institution.id = iuser.institutionId "
			+ "	JOIN User suser ON iuser.userId = suser.id " + "	WHERE suser.username = :username "
			+ "	AND UPPER(series.institution) = CASE WHEN :institution <> 'null' THEN UPPER(:institution) ELSE series.institution END "
			+ "	AND studyQueryAttrs.modsInStudy = 	CASE WHEN :modality <> 'null' THEN :modality ELSE studyQueryAttrs.modsInStudy END "
			+ "	AND patient.patSex		= CASE WHEN :gender <> 'null' THEN :gender ELSE patient.patSex END "
			+ "	AND studyQueryAttrs.numInstances = 	CASE WHEN :instances <> 0 THEN :instances ELSE studyQueryAttrs.numInstances END "
			+ "	AND UPPER(patientId.patId) 		LIKE CASE WHEN :patientId <> 'null' THEN UPPER(:patientId) ELSE UPPER(patientId.patId) END "
			+ "	AND ( UPPER(personName.middleName) LIKE CASE WHEN :name <> 'null' THEN UPPER(:name) ELSE UPPER(personName.middleName) END "
			+ "	OR UPPER(personName.familyName) LIKE CASE WHEN :name <> 'null' THEN UPPER(:name) ELSE UPPER(personName.familyName) END "
			+ "	OR UPPER(personName.givenName) 	LIKE CASE WHEN :name <> 'null' THEN UPPER(:name) ELSE UPPER(personName.givenName) END ) "
			+ "	AND UPPER(study.studyDesc) 		LIKE CASE WHEN :studyDescription <> 'null' THEN UPPER(:studyDescription) ELSE UPPER(study.studyDesc) END "
//			+ "	AND study.date BETWEEN :studyDateInit AND :studyDateEnd "
			
			+ "	AND ( (study.date >= :studyDateInit OR CAST(:studyDateInit as timestamp) IS NULL)  "
			+ "	AND  (study.date <= :studyDateEnd OR CAST(:studyDateEnd as timestamp) IS NULL) )"
			)
	public List<StudyFullDto> findFullStudiesByUsernameAndFilters(@Param("username") String username,
			@Param("name") String name, @Param("institution") String institution, @Param("gender") String gender,
			@Param("instances") Integer instances, @Param("modality") String modality,
			@Param("patientId") String patientId, @Param("studyDescription") String studyDescription, @Param("studyDateInit")  Date studyDateInit, @Param("studyDateEnd")  Date studyDateEnd);
}
