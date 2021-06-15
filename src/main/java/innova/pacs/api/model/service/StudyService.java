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

	@Value("${pacs.url}")
	private String pacsUrl;

	@Transactional(readOnly = true)
	public List<StudyDto> findByPatientFk(Integer patientFk) {
		return this.studyRepository.findByPatientFk(patientFk);
	}

	@Transactional(readOnly = true)
	public List<StudyFullDto> findFullStudies() {
		return this.studyRepository.findFullStudiesByUsername(SecurityUtil.getUsername());
	}

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
		// institution, patientId, patientSex, studyDesc, modsInStudy
	}

	@Transactional
	public void refactorSturyDate() {
		List<Study> lstStudy = this.studyRepository.getBySrudyDateOnNull();

		for (Study study : lstStudy) {
			if (study.getStudyDate() != null) {
				try {

					Date tradeDate = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(study.getStudyDate());
					study.setDate(tradeDate);

					studyRepository.save(study);
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
	public StudyNotificationDto findPatientByUiud(String studyIuid) throws Exception {
		StudyNotificationDto studyNotification = this.studyRepository.findPatientByUiud(studyIuid);

		if (studyNotification == null) {
			throw new Exception(String.format("No hay estudio para el Iuid: %s", studyIuid));
		}

		return studyNotification;
	}

	@Async
	public void sendNotification(String studyIuid) throws Exception {
		StudyNotificationDto studyNotification = this.findPatientByUiud(studyIuid);

		Map<String, String> templates = new HashMap<>();
		templates.put("patientName",
				String.format("%s %s %s",
						studyNotification.getFamilyName() != null ? studyNotification.getFamilyName() : "",
						studyNotification.getGivenName() != null ? studyNotification.getGivenName() : "",
						studyNotification.getMiddleName() != null ? studyNotification.getMiddleName() : ""));
		templates.put("url", String.format("%s/viewer.html?studyUID=%s", pacsUrl, studyNotification.getStudyIuid()));

		emailService.sendMessageWithAttachment(studyNotification.getEmail(), "Innova Pacs",
				SmtpUtil.transformFromTemplate(templates), null);
	}
}
