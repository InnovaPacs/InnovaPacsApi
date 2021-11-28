package innova.pacs.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import innova.pacs.api.dto.DiagnosisDto;
import innova.pacs.api.model.Diagnosis;

public interface IDiagnosisRepository extends PagingAndSortingRepository<Diagnosis, Long> {

	@Query(value = "select distinct new innova.pacs.api.dto.DiagnosisDto( "
			+ "diagnosis.id, "
			+ "diagnosis.created, "
			+ "diagnosis.studyId, "
			+ "diagnosis.fileId, "
			+ "diagnosis.title, "
			+ "user.username,"
			+ "iFile.mimeType ) "
			+ "from Diagnosis diagnosis "
			+ "join User user on user.id = diagnosis.creatorId "
			+ "left join InnovaFile iFile on diagnosis.fileId = iFile.id "
			+ "where diagnosis.studyId = :studyId ")
	List<DiagnosisDto> getByStudyId(@Param("studyId") Long studyId);
	
	List<Diagnosis> findByStudyId(@Param("studyId") Long studyId);
}
