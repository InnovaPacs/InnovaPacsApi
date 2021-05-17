package innova.pacs.api.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	public User update(Long id, User user) {
		Optional<User> optUser = this.findById(id);
		User currentUser = null;
		
		if(optUser.isPresent()) {
			currentUser = optUser.get();
			
			currentUser.setActive(user.getActive());
			currentUser.setUsername(user.getUsername());
			currentUser.setEmail(user.getEmail());
			
			if(user.getPassword() != null) {
				currentUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			}
			
			currentUser = this.userRepository.save(currentUser);
			currentUser.setPassword(null);
		}
	
		return currentUser;
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
	
	public UserDto getById(Long id) {
		return this.userRepository.getById(id);
	}
	
	public List<UserDto> userReportQuery(){
		return this.userRepository.userReportQuery();
	}
}
