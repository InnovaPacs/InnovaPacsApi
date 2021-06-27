package innova.pacs.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import innova.pacs.api.dto.InstitutionDto;
import innova.pacs.api.model.Series;

public interface ISeriesRepository extends PagingAndSortingRepository<Series, Long>{
	@Query(value = "SELECT distinct new  innova.pacs.api.dto.InstitutionDto ( "
			+ "series.institution "
			+ " ) "
			+ "FROM Series series ")
	public List<InstitutionDto> getAllInstitutions();
}
