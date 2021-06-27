package innova.pacs.api.dto;

import java.io.Serializable;

public class InstitutionUserDto implements Serializable {
	private static final long serialVersionUID = 8053764229593890212L;
	
	private Integer userId;
	private Integer institutionId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}
}
