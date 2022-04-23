package innova.pacs.api.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import innova.pacs.api.model.InnovaFile;
import innova.pacs.api.model.service.InnovaFileService;

@RestController
@RequestMapping("/api/v1/innovaFiles")
public class InnovaFileController {
	@Autowired
	private InnovaFileService innovaFileService;

	@GetMapping()
	public List<InnovaFile> getAllInstitutions() {
		return this.innovaFileService.findAll();
	}

	@PostMapping()
	public InnovaFile create(@RequestParam("file") MultipartFile file) {
		return this.innovaFileService.create(file);
	}

	@PutMapping
	public InnovaFile update(@RequestBody InnovaFile innovaFile) {
		return this.innovaFileService.update(innovaFile);
	}

	@GetMapping("/{id}/download")
	public void findById(@PathVariable("id") Long id, WebRequest request, HttpServletResponse response) {
		try {
			InnovaFile innovaFile = this.innovaFileService.findById(id);

			response.setContentType(innovaFile.getMimeType());
			response.setHeader("Content-Disposition", "inline; filename=\"" + innovaFile.getName() + "\"");
			response.setHeader("Cache-Control", "max-age=31536000, public");
			response.setContentLengthLong(innovaFile.getBin().length);
			response.getOutputStream().write(innovaFile.getBin());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
