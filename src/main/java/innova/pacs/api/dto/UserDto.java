package innova.pacs.api.dto;

import java.io.Serializable;

public class UserDto implements Serializable{
	private static final long serialVersionUID = 5521190601666601759L;
	
	private Long id;
	private String email;
	private String username;
	private Boolean active;
	
	
	public UserDto(Long id, String email, String username, Boolean active) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.active = active;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
}
