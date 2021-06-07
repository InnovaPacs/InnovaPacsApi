package innova.pacs.api.dto;

public class EmailConfigurationDto {
	private Integer patientPk;
	private String email;
	
	public Integer getPatientPk() {
		return patientPk;
	}
	public void setPatientPk(Integer patientPk) {
		this.patientPk = patientPk;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
