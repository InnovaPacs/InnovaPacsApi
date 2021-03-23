package innova.pacs.api.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import innova.pacs.api.model.User;

public interface IUserRepository  extends PagingAndSortingRepository<User, Long>{
	public User findByUsernameAndActive(String username, Boolean active);
	public User findByUsername(String username);
}
