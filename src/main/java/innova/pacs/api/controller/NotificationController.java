package innova.pacs.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import innova.pacs.api.model.service.EmailService;
import innova.pacs.api.model.service.StudyService;
import innova.pacs.api.security.SmtpUtil;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private StudyService studyService;
	
	@GetMapping("/test")
	public void sendNotifications() throws Exception {
		Map<String, String> templates = new HashMap<>();
		templates.put("patientName", "Jose Luis");
		templates.put("url", "http://localhost/viewer.html?studyUID=1.2.156.112601.1.2.327672685.2484.1592982969.967636");
		
		emailService.sendMessageWithAttachment("camposbj1990@gmail.com", "Ping", SmtpUtil.transformFromTemplate(templates), null);
	}
	
	@GetMapping("/{studyIuid}")
	public void sendNotifications(@PathVariable String studyIuid) throws Exception {
		this.studyService.sendNotification(studyIuid);
	}
}
