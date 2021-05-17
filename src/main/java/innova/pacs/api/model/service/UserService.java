package innova.pacs.api.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import innova.pacs.api.dto.UserDto;
import innova.pacs.api.model.User;
import innova.pacs.api.model.repository.IUserRepository;

@Service
public class UserService {
	@Autowired
	private IUserRepository userRepository;
	
	public Page<User> findAll(Pageable pageable) {
		return this.userRepository.findAll(pageable);
	}

	public Optional<User> findById(Long id) {
		return this.userRepository.findById(id);
	}

	public void deleteById(Long id) {
		this.userRepository.deleteById(id);
	}

	public User update(User user) {
		return this.userRepository.save(user);
	}

	public User create(User user) {
		return this.userRepository.save(user);
	}

	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}
	
	public List<UserDto> getAll(){
		return this.userRepository.getAll();
	}
}
