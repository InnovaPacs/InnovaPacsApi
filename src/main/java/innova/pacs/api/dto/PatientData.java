package innova.pacs.api.dto;

import java.util.Date;

public class PatientData {
	private Date birthDate;
	private String sex;
	private String middleName;
	private String familyName;
	private String givenName;

	public PatientData(Date birthDate, String sex, String middleName, String familyName, String givenName) {
		super();
		this.birthDate = birthDate;
		this.sex = sex;
		this.middleName = middleName;
		this.familyName = familyName;
		this.givenName = givenName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

}
