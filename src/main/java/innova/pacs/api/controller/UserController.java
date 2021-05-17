package innova.pacs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import innova.pacs.api.dto.UserDto;
import innova.pacs.api.model.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public List<UserDto> getAll() {
		return this.userService.getAll();
	}
}
