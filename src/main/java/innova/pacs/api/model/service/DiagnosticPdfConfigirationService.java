package innova.pacs.api.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innova.pacs.api.model.DiagnosticPdfConfigiration;
import innova.pacs.api.model.repository.IDiagnosticPdfConfigirationRepository;

@Service
public class DiagnosticPdfConfigirationService {
	@Autowired
	private IDiagnosticPdfConfigirationRepository diagnosticPdfConfigirationRepository;

	/**
	 * Save
	 * 
	 * @param innovaFile
	 * @return
	 */
	public DiagnosticPdfConfigiration create(DiagnosticPdfConfigiration diagnosticPdfConfigiration) {
		return diagnosticPdfConfigirationRepository.save(diagnosticPdfConfigiration);
	}

	/**
	 * Update file
	 * 
	 * @param innovaFile
	 * @return
	 */
	public DiagnosticPdfConfigiration update(DiagnosticPdfConfigiration diagnosticPdfConfigiration) {
		return this.diagnosticPdfConfigirationRepository.save(diagnosticPdfConfigiration);
	}

	/**
	 * Find by id
	 * 
	 * @param id
	 * @return
	 */
	public DiagnosticPdfConfigiration findById(Long id) {
		Optional<DiagnosticPdfConfigiration> optFile = this.diagnosticPdfConfigirationRepository.findById(id);
		return optFile.get();
	}

	/**
	 * Find by study id
	 * 
	 * @param studyId
	 * @return
	 */
	public List<DiagnosticPdfConfigiration> findAll() {
		return (List<DiagnosticPdfConfigiration>) this.diagnosticPdfConfigirationRepository.findAll();
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<DiagnosticPdfConfigiration> findByUserId(Long id) {
		return this.diagnosticPdfConfigirationRepository.findByUserId(id);
	}
}
