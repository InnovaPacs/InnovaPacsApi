package innova.pacs.api.dto;

public class StudyNotificationDto {
	private String studyIuid;
	private String email;
	private String familyName;
	private String givenName;
	private String middleName;
	
	public StudyNotificationDto(String studyIuid, String email, String familyName, String givenName, String middleName) {
		super();
		this.studyIuid = studyIuid;
		this.email = email;
		this.familyName = familyName;
		this.givenName = givenName;
		this.middleName = middleName;
	}
	
	public String getStudyIuid() {
		return studyIuid;
	}
	public void setStudyIuid(String studyIuid) {
		this.studyIuid = studyIuid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
}
