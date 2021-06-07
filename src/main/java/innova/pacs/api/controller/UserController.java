package innova.pacs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import innova.pacs.api.dto.UserDto;
import innova.pacs.api.model.User;
import innova.pacs.api.model.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_ROOT"})
	@GetMapping("/")
	public List<UserDto> getAll() {
		return this.userService.userReportQuery();
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_ROOT"})
	@PostMapping("/")
	public User Usercreate(@RequestBody User user) {
		return this.userService.create(user);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_ROOT"})
	@PutMapping("/{id}")
	public User update(@PathVariable("id") Long id, @RequestBody User user) {
		return this.userService.update(id, user);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_ROOT"})
	@GetMapping("/{id}")
	public UserDto getById(@PathVariable("id") Long id) {
		return this.userService.getById(id);
	}
}
