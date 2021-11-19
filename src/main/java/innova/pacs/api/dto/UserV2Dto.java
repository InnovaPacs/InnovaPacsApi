package innova.pacs.api.dto;

import java.util.List;

public class UserV2Dto {
	private Long id;
	private String email;
	private String username;
	private Boolean active;
	private List<String> institutions;
	
	public UserV2Dto(Long id, String email, String username, Boolean active) {
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
	public List<String> getInstitutions() {
		return institutions;
	}
	public void setInstitutions(List<String> institutions) {
		this.institutions = institutions;
	}
	
		
}
