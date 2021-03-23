package innova.pacs.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import innova.pacs.api.dto.StudyDto;
import innova.pacs.api.model.Study;

public interface IStudyRepository extends PagingAndSortingRepository<Study, Integer>{
	@Query(value = "SELECT new innova.pacs.api.dto.StudyDto( "
			+ "study.pk, "
			+ "study.createdTime, "
			+ "study.studyDate, "
			+ "study.studyId, "
			+ "study.studyIuid,"
			+ "study.patientFk "
			+ " ) "
			+ " FROM "
			+ "	Study study "
			+ " WHERE study.patientFk = :patientFk ")
	public List<StudyDto> findByPatientFk(@Param("patientFk") Integer patientFk);
}
