package innova.pacs.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import innova.pacs.api.dto.InstitutionConfigurtionDto;
import innova.pacs.api.model.InstitutionUser;

public interface IInstitutionUserRepository  extends PagingAndSortingRepository<InstitutionUser, Integer>{

	@Query(value =" select distinct institution.name "
			+ " from InstitutionUser iu " 
			+ " join Institution institution ON iu.institutionId = institution.id "
			+ " where iu.userId = :userId ")
	public List<String> findInstitutionByUserId(@Param("userId") Long userId);
	
	@Query(value = "select new innova.pacs.api.dto.InstitutionConfigurtionDto( "
			+ " institution.id, "
			+ "	institution.name, "
			+ " ( select "
			+ "		CASE WHEN count(0) > 0 THEN true ELSE false END"
			+ " 	from InstitutionUser iu "
			+ "		where iu.userId =:userId ) "
			+ ") "
			+ "from "
			+ "Institution institution")
	public List<InstitutionConfigurtionDto> getConfiguration(@Param("userId") Long userId);
	
	@Modifying
	@Query("delete from InstitutionUser iu where iu.userId =:userId")
	void deleteConfigurationByUserId(@Param("userId") Long userId);
}
