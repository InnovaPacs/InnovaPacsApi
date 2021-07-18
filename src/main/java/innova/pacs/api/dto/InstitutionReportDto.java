package innova.pacs.api.dto;

public class InstitutionReportDto {
	private Integer institutionId;
	private Long numberStuty;
	private Long numberInstances;
	private Long numberPatients;
	
	public InstitutionReportDto(Integer institutionId, Long numberStuty, Long numberInstances, Long numberPatients) {
		super();
		this.institutionId = institutionId;
		this.numberStuty = numberStuty;
		this.numberInstances = numberInstances;
		this.numberPatients = numberPatients;
	}
	
	public Integer getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}
	public Long getNumberStuty() {
		return numberStuty;
	}
	public void setNumberStuty(Long numberStuty) {
		this.numberStuty = numberStuty;
	}
	public Long getNumberInstances() {
		return numberInstances;
	}
	public void setNumberInstances(Long numberInstances) {
		this.numberInstances = numberInstances;
	}
	public Long getNumberPatients() {
		return numberPatients;
	}
	public void setNumberPatients(Long numberPatients) {
		this.numberPatients = numberPatients;
	}
}
