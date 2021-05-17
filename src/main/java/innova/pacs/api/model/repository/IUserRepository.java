package innova.pacs.api.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import innova.pacs.api.dto.UserDto;
import innova.pacs.api.model.User;

public interface IUserRepository  extends PagingAndSortingRepository<User, Long>{
	public User findByUsernameAndActive(String username, Boolean active);
	public User findByUsername(String username);
	@Query(value = "SELECT new innova.pacs.api.dto.UserDto ("
			+ " user.id,"
			+ "	user.email,"
			+ "	user.username,"
			+ "	user.active )"
			+ "FROM User user ORDER BY ID DESC")
	public List<UserDto> getAll();
}
