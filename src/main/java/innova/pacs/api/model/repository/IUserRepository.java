package innova.pacs.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import innova.pacs.api.dto.UserDto;
import innova.pacs.api.dto.UserV2Dto;
import innova.pacs.api.model.User;

public interface IUserRepository  extends PagingAndSortingRepository<User, Long>{
	public User findByUsernameAndActive(String username, Boolean active);
	public User findByUsername(String username);
	@Query(value = "SELECT new innova.pacs.api.dto.UserDto ("
			+ " user.id,"
			+ "	user.email,"
			+ "	user.username, "
			+ "	user.active )"
			+ "FROM User user ORDER BY ID DESC")
	public List<UserDto> getAll();
	
	@Query(value = "SELECT new innova.pacs.api.dto.UserDto ("
			+ " user.id,"
			+ "	user.email,"
			+ "	user.username,"
			+ "	user.active )"
			+ "FROM User user WHERE user.id = :id")
	public UserDto getById(@Param("id") Long id);
	
	@Query(nativeQuery = true)
	public List<UserDto> userReportQuery();
	
	@Query(value = "SELECT distinct new innova.pacs.api.dto.UserV2Dto ("
			+ " user.id,"
			+ "	user.email,"
			+ "	user.username,"
			+ "	user.active )"
			+ "FROM User user")
	public List<UserV2Dto> userReport();
}
