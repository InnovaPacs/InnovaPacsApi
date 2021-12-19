package innova.pacs.api.model.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import innova.pacs.api.dto.DiagnosisDto;
import innova.pacs.api.dto.InnovaDiagnosticPdfDto;
import innova.pacs.api.dto.PatientData;
import innova.pacs.api.model.Diagnosis;
import innova.pacs.api.model.DiagnosticPdfConfigiration;
import innova.pacs.api.model.DoctorProfile;
import innova.pacs.api.model.InnovaFile;
import innova.pacs.api.model.User;
import innova.pacs.api.model.repository.IDiagnosisRepository;
import innova.pacs.api.util.PdfUtil;

@Service
public class DiagnosisService {
	@Autowired
	private IDiagnosisRepository diagnosisRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private DiagnosticPdfConfigirationService diagnosticPdfConfigirationService;
	@Autowired
	private DoctorProfileService doctorProfileService;
	@Autowired
	private InnovaFileService innovFileService;
	@Autowired
	private InnovaFileDiagnosticService innovaFileDiagnosticService;
	@Autowired
	private StudyService studyService;

	/**
	 * Save file
	 * 
	 * @param diagnosis
	 * @return
	 * @throws IOException
	 */
	public Diagnosis create(Diagnosis diagnosis) throws IOException {
		User user = this.userService.getUserInsession();
		diagnosis.setCreatorId(user.getId());

		/**
		 * In this case we need to generate the file
		 */
		if (diagnosis.getFileId() == null && diagnosis.getDiagnosis() != null) {
			InnovaDiagnosticPdfDto InnovaDiagnosticPdfDto = this.getDiagnosticProfile(user, diagnosis);
			ByteArrayInputStream byteArrayInputStream = this.innovaFileDiagnosticService
					.preview(InnovaDiagnosticPdfDto);

			byte[] array = new byte[byteArrayInputStream.available()];
			byteArrayInputStream.read(array);

			InnovaFile diagnosisFile = new InnovaFile();
			diagnosisFile.setBin(array);
			diagnosisFile.setCreated(new Date());
			diagnosisFile.setCreatorId(user.getId());
			diagnosisFile.setMimeType("application/pdf");
			diagnosisFile.setName(String.format("%s_%s.pdf", diagnosis.getTitle(), new Date().getTime()));

			diagnosisFile = this.innovFileService.create(diagnosisFile);
			diagnosis.setFileId(diagnosisFile.getId());
		}

		return this.diagnosisRepository.save(diagnosis);
	}

	/**
	 * Update file
	 * 
	 * @param innovaFile
	 * @return
	 */
	public Diagnosis update(Diagnosis diagnosis) {
		return this.diagnosisRepository.save(diagnosis);
	}

	/**
	 * Find by id
	 * 
	 * @param id
	 * @return
	 */
	public Diagnosis findById(Long id) {
		Optional<Diagnosis> optFile = this.diagnosisRepository.findById(id);
		return optFile.get();
	}

	/**
	 * Find by study id
	 * 
	 * @param studyId
	 * @return
	 */
	public List<DiagnosisDto> findByStudyId(Integer studyId) {
		return this.diagnosisRepository.getByStudyId(studyId);
	}

	private InnovaDiagnosticPdfDto getDiagnosticProfile(User user, Diagnosis diagnosis) {
		InnovaDiagnosticPdfDto innovaDiagnosticPdf = new InnovaDiagnosticPdfDto();

		List<DiagnosticPdfConfigiration> diagnosticPdfConfigiration = this.diagnosticPdfConfigirationService
				.findByUserId(user.getId());
		DiagnosticPdfConfigiration currentDiagnosticPdfConfigiration = diagnosticPdfConfigiration.get(0);

		DoctorProfile doctorProfile = this.doctorProfileService.findByUserId();
		InnovaFile logo = this.innovFileService.findById(currentDiagnosticPdfConfigiration.getInnovaFileId());

		PatientData patientData = this.studyService.getPatientDataByStudyId(diagnosis.getStudyId());

		innovaDiagnosticPdf.setDiagnostic(diagnosis.getDiagnosis());

		innovaDiagnosticPdf.setDoctorName(String.format("%s %s", doctorProfile.getName(), doctorProfile.getLastName()));
		innovaDiagnosticPdf.setDoctorTitle(doctorProfile.getTitle());
		innovaDiagnosticPdf.setDoctorUniversity(doctorProfile.getCollege());
		innovaDiagnosticPdf.setDoctorLicense(doctorProfile.getLicense());

		innovaDiagnosticPdf.setHeaderColor(currentDiagnosticPdfConfigiration.getHeaderColor());
		innovaDiagnosticPdf.setLogo(logo);
		innovaDiagnosticPdf.setDiagnosticDate(PdfUtil.formatSignatureDate(new Date()));

		if(patientData.getBirthDate() != null) {
			LocalDate birthDate = PdfUtil.convertDateToLocalDate(patientData.getBirthDate());
			Integer age = PdfUtil.getAgeFromBirthDate(birthDate);
			innovaDiagnosticPdf.setPatientAge(String.format("%s años", age));
			innovaDiagnosticPdf.setPatientBirth(PdfUtil.formatBirthDate(patientData.getBirthDate()));
		}
		
		innovaDiagnosticPdf.setPatientName(
				String.format("%s %s %s", patientData.getGivenName() != null ? patientData.getGivenName() : "",
						patientData.getMiddleName() != null ? patientData.getMiddleName() : "",
						patientData.getFamilyName() != null ? patientData.getFamilyName() : ""));
		innovaDiagnosticPdf.setPatientSex(patientData.getSex());

		return innovaDiagnosticPdf;
	}

}
