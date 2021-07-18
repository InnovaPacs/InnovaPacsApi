package innova.pacs.api.dto;

public class ModalityReportDto {
	private String modality;
	private Long numberStuty;
	private Long numberInstances;
	private Long numberPatients;
	
	public ModalityReportDto(String modality, Long numberStuty, Long numberInstances, Long numberPatients) {
		super();
		this.modality = modality;
		this.numberStuty = numberStuty;
		this.numberInstances = numberInstances;
		this.numberPatients = numberPatients;
	}
	
	public String getModality() {
		return modality;
	}
	public void setModality(String modality) {
		this.modality = modality;
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
