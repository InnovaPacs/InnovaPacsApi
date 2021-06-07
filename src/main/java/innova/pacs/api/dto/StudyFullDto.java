package innova.pacs.api.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudyFullDto implements Serializable {
	private static final long serialVersionUID = 5864161564244656883L;

	private Integer patientPk;
	private String patientId;
	private String middleName;
	private String familyName;
	private String givenName;
	private String patientSex;
	private Integer studyPk;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdTime;
	private String studyDate;
	private String patientBithDate;
	private String studyId;
	private String iuid;
	private String desc;
	private String modality;
	private Integer numInstances;
	private Date date;
	private Date birthDate;
	private String institution;
	

	public StudyFullDto(Integer patientPk, String patientId, String middleName, String familyName, String givenName,
			String patientSex, Integer studyPk, Date studyCreatedTime, Date studyDate, String studyId,
			String studyIuid, String studyDesc, String seriesModality, Integer numInstances, Date patientBithDate, String institution) {
		super();
		this.patientPk = patientPk;
		this.patientId = patientId;
		this.middleName = middleName;
		this.familyName = familyName;
		this.givenName = givenName;
		this.patientSex = patientSex;
		this.studyPk = studyPk;
		this.createdTime = studyCreatedTime;
		this.date = studyDate;
		this.studyId = studyId;
		this.iuid = studyIuid;
		this.desc = studyDesc;
		this.modality = seriesModality;
		this.numInstances = numInstances;
		this.birthDate = patientBithDate;
		this.institution = institution;
		
	}

	public Integer getPatientPk() {
		return patientPk;
	}

	public void setPatientPk(Integer patientPk) {
		this.patientPk = patientPk;
	}

	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	public Integer getStudyPk() {
		return studyPk;
	}

	public void setStudyPk(Integer studyPk) {
		this.studyPk = studyPk;
	}

	public String getStudyDate() {
		return studyDate;
	}

	public void setStudyDate(String studyDate) {
		this.studyDate = studyDate;
	}

	public String getStudyId() {
		return studyId;
	}

	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getIuid() {
		return iuid;
	}

	public void setIuid(String iuid) {
		this.iuid = iuid;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public Integer getNumInstances() {
		return numInstances;
	}

	public void setNumInstances(Integer numInstances) {
		this.numInstances = numInstances;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPatientBithDate() {
		return patientBithDate;
	}

	public void setPatientBithDate(String patientBithDate) {
		this.patientBithDate = patientBithDate;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}
}
