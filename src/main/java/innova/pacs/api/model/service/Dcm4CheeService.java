package innova.pacs.api.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innova.pacs.api.client.Dcm4cheeClient;
import innova.pacs.api.dto.AETDto;

@Service
public class Dcm4CheeService {
	@Autowired
	private Dcm4cheeClient dcm4cheeClient;
	
	public List<AETDto> getAllAet() throws Exception{
		return this.dcm4cheeClient.getAes();
	}
	
	/**
	 * Export study
	 * @param uuid
	 * @param aets
	 * @throws Exception
	 */
	public void export(String uuid, String aets) throws Exception{
		this.dcm4cheeClient.export(uuid,aets);
	}
}
