package innova.pacs.api.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import innova.pacs.api.model.Institution;

public interface IInstitutionRepository extends PagingAndSortingRepository<Institution, Integer>{
	public Institution findByName(@Param("name") String name);
}
