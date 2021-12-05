package innova.pacs.api.dto;

import java.io.Serializable;

public class StudyFullCountDto implements Serializable {
	private static final long serialVersionUID = 2904962226953876038L;

	private Integer studyCount;
	private Integer instancesCount;
	private Integer modalityCount;

	public StudyFullCountDto(Integer studyCount, Integer instancesCount, Integer modalityCount) {
		super();
		this.studyCount = studyCount;
		this.instancesCount = instancesCount;
		this.modalityCount = modalityCount;
	}

	public Integer getStudyCount() {
		return studyCount;
	}

	public void setStudyCount(Integer studyCount) {
		this.studyCount = studyCount;
	}

	public Integer getInstancesCount() {
		return instancesCount;
	}

	public void setInstancesCount(Integer instancesCount) {
		this.instancesCount = instancesCount;
	}

	public Integer getModalityCount() {
		return modalityCount;
	}

	public void setModalityCount(Integer modalityCount) {
		this.modalityCount = modalityCount;
	}

}
