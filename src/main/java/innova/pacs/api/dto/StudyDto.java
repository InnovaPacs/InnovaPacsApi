package innova.pacs.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudyDto {
	private Integer pk;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdTime;
	private String studyDate;
	private String studyId;
	private String studyIuid;
	private Integer patientFk;

	
	public StudyDto(Integer pk, Date createdTime, String studyDate, String studyId, String studyIuid,
			Integer patientFk) {
		super();
		this.pk = pk;
		this.createdTime = createdTime;
		this.studyDate = studyDate;
		this.studyId = studyId;
		this.studyIuid = studyIuid;
		this.patientFk = patientFk;
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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

	public String getStudyIuid() {
		return studyIuid;
	}

	public void setStudyIuid(String studyIuid) {
		this.studyIuid = studyIuid;
	}

	public Integer getPatientFk() {
		return patientFk;
	}

	public void setPatientFk(Integer patientFk) {
		this.patientFk = patientFk;
	}

}
