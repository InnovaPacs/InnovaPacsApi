package innova.pacs.api.controller;

import java.io.Serializable;
import java.util.Date;

public class StudyFilterDto implements Serializable{
	private static final long serialVersionUID = 716317810011940425L;
	
	private String name;
	private String institution;
	private String patientId;
	private String gender;
	private String studyDescription;
	private String modality;
	private Integer instances;
	private Date studyDateInit;
	private Date studyDateEnd;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStudyDescription() {
		return studyDescription;
	}
	public void setStudyDescription(String studyDescription) {
		this.studyDescription = studyDescription;
	}
	public String getModality() {
		return modality;
	}
	public void setModality(String modality) {
		this.modality = modality;
	}
	public Integer getInstances() {
		return instances;
	}
	public void setInstances(Integer instances) {
		this.instances = instances;
	}
	public Date getStudyDateInit() {
		return studyDateInit;
	}
	public void setStudyDateInit(Date studyDateInit) {
		this.studyDateInit = studyDateInit;
	}
	public Date getStudyDateEnd() {
		return studyDateEnd;
	}
	public void setStudyDateEnd(Date studyDateEnd) {
		this.studyDateEnd = studyDateEnd;
	}
}
