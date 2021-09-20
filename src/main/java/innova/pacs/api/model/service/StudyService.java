package innova.pacs.api.model.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import innova.pacs.api.dto.ModalityReportDto;
import innova.pacs.api.client.Dcm4cheeClient;
import innova.pacs.api.dto.InstitutionReportDto;
import innova.pacs.api.dto.InstitutionStudyDto;
import innova.pacs.api.dto.StudyDto;
import innova.pacs.api.dto.StudyFullDto;
import innova.pacs.api.dto.StudyNotificationDto;
import innova.pacs.api.model.Study;
import innova.pacs.api.model.repository.IStudyRepository;
import innova.pacs.api.security.SecurityUtil;
import innova.pacs.api.security.SmtpUtil;

@Service
public class StudyService {
	@Autowired
	private EmailService emailService;

	@Autowired
	private IStudyRepository studyRepository;
	@Autowired
	private Dcm4cheeClient dcm4cheeClient;

	@Value("${previewer.host}")
	private String previewerHost;
	
	@Value("${previewer.port}")
	private String previewerPort;

	/**
	 * Find By Patient Fk
	 * @param patientFk
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<StudyDto> findByPatientFk(Integer patientFk) {
		return this.studyRepository.findByPatientFk(patientFk);
	}

	/**
	 * Find Full Studies
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<StudyFullDto> findFullStudies() {
		this.dcm4cheeClient.updateStudies();
		return this.studyRepository.findFullStudiesByUsername(SecurityUtil.getUsername());
	}

	/**
	 * Find Full Studies Filter
	 * @param name
	 * @param institution
	 * @param gender
	 * @param instances
	 * @param modality
	 * @param patientId
	 * @param studyDateEnd
	 * @param studyDateInit
	 * @param studyDescription
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<StudyFullDto> findFullStudiesFilter(String name, String institution, String gender, Integer instances,
			String modality, String patientId, String studyDateEnd, String studyDateInit, String studyDescription) {
		Date daateEnd = null;
		Date dateInit = null;
		try {
			name = !"null".equals(name) ? String.format("%%%s%%", name) : "null";
			patientId = !"null".equals(patientId) ? String.format("%%%s%%", patientId) : "null";
			studyDescription = !"null".equals(studyDescription) ? String.format("%%%s%%", studyDescription) : "null";

			dateInit = !"".equals(studyDateInit) && !"null".equals(studyDateInit) && studyDateInit != null
					? new SimpleDateFormat("yyyy-MM-dd").parse(studyDateInit)
					: null;
			daateEnd = !"".equals(studyDateEnd) && !"null".equals(studyDateEnd) && studyDateEnd != null
					? new SimpleDateFormat("yyyy-MM-dd").parse(studyDateEnd)
					: null;

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return this.studyRepository.findFullStudiesByUsernameAndFilters(SecurityUtil.getUsername(), name, institution,
				gender, instances, modality, patientId, studyDescription, dateInit, daateEnd);
	}

	/**
	 * Refactor Study Date
	 */
	@Transactional
	public void refactorStudyDate() {
		List<Study> lstStudy = this.studyRepository.getBySrudyDateOnNull();

		for (Study study : lstStudy) {
			if (study.getStudyDate() != null) {
				try {

					Date tradeDate = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(study.getStudyDate());
					study.setDate(tradeDate);

					this.studyRepository.save(study);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Find Patient By Uiud
	 * 
	 * @param studyIuid
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)	
	public StudyNotificationDto findPatientByUiud(String studyIuid) throws Exception {
		StudyNotificationDto studyNotification = this.studyRepository.findPatientByUiud(studyIuid);

		if (studyNotification == null) {
			throw new Exception(String.format("No hay estudio para el Iuid: %s", studyIuid));
		}

		return studyNotification;
	}

	/**
	 * Send notification
	 * @param studyIuid
	 * @throws Exception
	 */
	@Async
	public void sendNotification(String studyIuid) throws Exception {
		StudyNotificationDto studyNotification = this.findPatientByUiud(studyIuid);

		Map<String, String> templates = new HashMap<>();
		templates.put("patientName",
				String.format("%s %s %s",
						studyNotification.getFamilyName() != null ? studyNotification.getFamilyName() : "",
						studyNotification.getGivenName() != null ? studyNotification.getGivenName() : "",
						studyNotification.getMiddleName() != null ? studyNotification.getMiddleName() : ""));
		templates.put("url", String.format("http://%s:%s/viewer.html?studyUID=%s", this.previewerHost, this.previewerPort, studyNotification.getStudyIuid()));

		emailService.sendMessageWithAttachment(studyNotification.getEmail(), "Innova Pacs",
				SmtpUtil.transformFromTemplate(templates), null);
	}
	
	/**
	 * Configure institutions and studies
	 */
	public void configureIntitutionStudies() {
 		List<InstitutionStudyDto> lstInsitutionStudy = this.studyRepository.findStudiesToConfigure();
 		
 		for (InstitutionStudyDto institutionStudyDto : lstInsitutionStudy) {
			Study study = this.studyRepository.findByPk(institutionStudyDto.getStudyPk());
			study.setInstitutionFk(institutionStudyDto.getInstitutionId());
			this.studyRepository.save(study);
		}
	}
	
	/**
	 * Get Modality report by institution
	 * @param institutionId
	 * @return
	 */
	public List<ModalityReportDto> modalityReportByInstitution(Integer institutionId) {
		return this.studyRepository.modalityReportByInstitution(institutionId);
	}
	
	/**
	 * Get Institution report by institution
	 * @param institutionId
	 * @return
	 */
	public List<InstitutionReportDto> institutionRepository(Integer institutionId){
		return this.studyRepository.institutionRepository(institutionId);
	}
}
