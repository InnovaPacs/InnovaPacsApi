package innova.pacs.api.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import innova.pacs.api.model.InnovaFile;
import innova.pacs.api.model.User;
import innova.pacs.api.model.repository.IInnovaFileReporsitory;

@Service
public class InnovaFileService {
	@Autowired
	private IInnovaFileReporsitory innovaFileReporsitory;
	@Autowired
	private UserService userService;

	/**
	 * Create file from MultipartFile
	 * 
	 * @param innovaFile
	 * @return
	 */
	public InnovaFile create(MultipartFile file) {
		InnovaFile innovaFile = new InnovaFile();

		try {
			User user = this.userService.getUserInsession();
			
			MediaType mediaType = MediaTypeFactory.getMediaType(file.getOriginalFilename()).get();

			innovaFile.setName(file.getOriginalFilename());
			innovaFile.setBin(file.getBytes());
			innovaFile.setMimeType(mediaType.toString());
			innovaFile.setCreatorId(user.getId());
			innovaFile = this.innovaFileReporsitory.save(innovaFile);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return innovaFile;
	}

	/**
	 * Update file
	 * 
	 * @param innovaFile
	 * @return
	 */
	public InnovaFile update(InnovaFile innovaFile) {
		return this.innovaFileReporsitory.save(innovaFile);
	}

	/**
	 * Find by id
	 * 
	 * @param id
	 * @return
	 */
	public InnovaFile findById(Long id) {
		Optional<InnovaFile> optFile = this.innovaFileReporsitory.findById(id);
		return optFile.get();
	}

	/**
	 * Find by study id
	 * 
	 * @param studyId
	 * @return
	 */
	public List<InnovaFile> findAll() {
		return (List<InnovaFile>) this.innovaFileReporsitory.findAll();
	}
	
	/**
	 * Create file from entity
	 * @param file
	 * @return
	 */
	public InnovaFile create(InnovaFile file) {
		InnovaFile innovaFile = this.innovaFileReporsitory.save(file);
		return innovaFile;
	}
}
