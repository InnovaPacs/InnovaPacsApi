package innova.pacs.api.dto;

public class InstitutionStudyDto {
	private Long institutionId;
	private String institutionName;
	private Integer studyPk;

	public InstitutionStudyDto(Long institutionId, String institutionName, Integer studyPk) {
		super();
		this.institutionId = institutionId;
		this.institutionName = institutionName;
		this.studyPk = studyPk;
	}

	public Long getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Long institutionId) {
		this.institutionId = institutionId;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public Integer getStudyPk() {
		return studyPk;
	}

	public void setStudyPk(Integer studyPk) {
		this.studyPk = studyPk;
	}

}
