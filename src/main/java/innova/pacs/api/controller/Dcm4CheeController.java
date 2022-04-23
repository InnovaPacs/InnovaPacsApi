package innova.pacs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import innova.pacs.api.dto.AETDto;
import innova.pacs.api.dto.AETExportDto;
import innova.pacs.api.model.service.Dcm4CheeService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/dcm4Chee")
public class Dcm4CheeController {
	@Autowired
	private Dcm4CheeService dcm4CheeService;

	@GetMapping("/aets")
	public List<AETDto> getAllAet() throws Exception {
		return dcm4CheeService.getAllAet();
	}

	@PostMapping("/export")
	public void export(@RequestBody AETExportDto aetExportDto) throws Exception {
		dcm4CheeService.export(aetExportDto.getUuid(), aetExportDto.getAets());
	}

}
