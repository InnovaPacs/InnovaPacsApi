package innova.pacs.api.dto;

public class InstitutionStudyDto {
	private Integer institutionId;
	private String institutionName;
	private Integer studyPk;

	public InstitutionStudyDto(Integer institutionId, String institutionName, Integer studyPk) {
		super();
		this.institutionId = institutionId;
		this.institutionName = institutionName;
		this.studyPk = studyPk;
	}

	public Integer getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Integer institutionId) {
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
