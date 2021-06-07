package innova.pacs.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import innova.pacs.api.dto.ModalityDto;
import innova.pacs.api.model.StudyQueryAttrs;

public interface IStudyQueryAttrsRepository  extends PagingAndSortingRepository<StudyQueryAttrs, Integer>{
	@Query(value = "SELECT DISTINCT NEW innova.pacs.api.dto.ModalityDto (attrs.modsInStudy) FROM StudyQueryAttrs attrs")
	public List<ModalityDto> getAllModalities();
}
