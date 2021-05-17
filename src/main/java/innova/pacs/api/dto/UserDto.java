package innova.pacs.api.dto;

import java.io.Serializable;

public class UserDto implements Serializable {
	private static final long serialVersionUID = 5521190601666601759L;

	private Long id;
	private String email;
	private String username;
	private String password;
	private Boolean active;
	private String institutions;

	public UserDto(Long id, String email, String username, Boolean active) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.active = active;
	}
	

	public UserDto(Long id, String email, String username, Boolean active, String institutions) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.active = active;
		this.institutions = institutions;
	}

	public UserDto(Long id, String email, String username, String password, Boolean active, String institutions) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.active = active;
		this.institutions = institutions;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInstitutions() {
		return institutions;
	}

	public void setInstitutions(String institutions) {
		this.institutions = institutions;
	}

}
