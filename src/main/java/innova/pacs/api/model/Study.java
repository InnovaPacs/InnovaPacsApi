package innova.pacs.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "study")
public class Study {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pk;
	private Date createdTime;
	private String studyDate;
	private String studyId;
	private String studyIuid;
	private Integer patientFk;
	private String studyDesc;
	private Date date;

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
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

	public String getStudyDesc() {
		return studyDesc;
	}

	public void setStudyDesc(String studyDesc) {
		this.studyDesc = studyDesc;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
