package innova.pacs.api.dto;

import java.io.Serializable;

public class AETExportDto implements Serializable {
	private static final long serialVersionUID = 7673111078376932562L;

	private String aets;
	private String uuid;

	public String getAets() {
		return aets;
	}

	public void setAets(String aets) {
		this.aets = aets;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
