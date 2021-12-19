package innova.pacs.api.dto;

import java.util.Date;

public class DiagnosisDto {
	private Long id;
	private Date created;
	private Integer studyId;
	private Long fileId;
	private String title;
	private String creator;
	private String mimeType;

	public DiagnosisDto(Long id, Date created, Integer studyId, Long fileId, String title, String creator, String mimeType) {
		super();
		this.id = id;
		this.created = created;
		this.studyId = studyId;
		this.fileId = fileId;
		this.title = title;
		this.creator = creator;
		this.mimeType = mimeType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getStudyId() {
		return studyId;
	}

	public void setStudyId(Integer studyId) {
		this.studyId = studyId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}
