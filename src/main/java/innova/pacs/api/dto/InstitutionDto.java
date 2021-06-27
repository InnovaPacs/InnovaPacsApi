package innova.pacs.api.dto;

public class InstitutionDto {
	private Long id;
	private String name;
	
	public InstitutionDto(Long id, String code, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public InstitutionDto(String name) {
		super();
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
