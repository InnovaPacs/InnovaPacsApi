package innova.pacs.api.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import innova.pacs.api.model.User;
import innova.pacs.api.model.repository.IUserRepository;

@Service
public class UserDetailService implements UserDetailsService{
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Error with credentials");
		}

		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.peek(authority -> System.out.println("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.getActive(), true, true, true, authorities);
	}
}
