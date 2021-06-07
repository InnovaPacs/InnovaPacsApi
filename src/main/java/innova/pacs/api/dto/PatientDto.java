package innova.pacs.api.dto;

public class PatientDto {
	private Integer pk;
	private String patId;
	private String middleName;
	private String familyName;
	private String givenName;
	private String gender;
	private String email; 

	public PatientDto(Integer pk, String patId, String middleName, String familyName, String givenName, String gender, String email) {
		super();
		this.pk = pk;
		this.patId = patId;
		this.middleName = middleName;
		this.familyName = familyName;
		this.givenName = givenName;
		this.gender = gender;
		this.email = email;
	}

	public String getPatId() {
		return patId;
	}

	public void setPatId(String patId) {
		this.patId = patId;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
