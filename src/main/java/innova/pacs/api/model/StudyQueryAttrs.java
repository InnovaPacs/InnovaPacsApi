package innova.pacs.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "study_query_attrs")
public class StudyQueryAttrs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pk;
	private String modsInStudy;
	private Integer numInstances;
	private Integer studyFk;

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public String getModsInStudy() {
		return modsInStudy;
	}

	public void setModsInStudy(String modsInStudy) {
		this.modsInStudy = modsInStudy;
	}

	public Integer getNumInstances() {
		return numInstances;
	}

	public void setNumInstances(Integer numInstances) {
		this.numInstances = numInstances;
	}

	public Integer getStudyFk() {
		return studyFk;
	}

	public void setStudyFk(Integer studyFk) {
		this.studyFk = studyFk;
	}
}
