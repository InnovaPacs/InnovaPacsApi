package innova.pacs.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pk;
	private Integer patientIdFk;
	private Integer patNameFk;
	private String patSex;
	private String patBirthdate;

	public Integer getPatientIdFk() {
		return patientIdFk;
	}

	public void setPatientIdFk(Integer patientIdFk) {
		this.patientIdFk = patientIdFk;
	}

	public Integer getPatNameFk() {
		return patNameFk;
	}

	public void setPatNameFk(Integer patNameFk) {
		this.patNameFk = patNameFk;
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public String getPatSex() {
		return patSex;
	}

	public void setPatSex(String patSex) {
		this.patSex = patSex;
	}
	
}
