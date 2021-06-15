package innova.pacs.api.model.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	@Autowired
    private JavaMailSender emailSender;
	
	/**
	 * Send email
	 * @param to
	 * @param subject
	 * @param text
	 * @param pathToAttachment
	 */
	public void sendMessageWithAttachment(
	  String to, String subject, String text, String pathToAttachment) {
	    MimeMessage message = emailSender.createMimeMessage();
	     
	    MimeMessageHelper helper;
		try {
			
			helper = new MimeMessageHelper(message, true);
		    helper.setFrom("camposbj1990@gmail.com");
		    helper.setTo(to);
		    helper.setSubject(subject);
		    helper.setText(text, true);
		     
		    if(pathToAttachment !=null) {
		    	FileSystemResource file 
			      = new FileSystemResource(new File(pathToAttachment));
			    helper.addAttachment("Invoice", file);	
		    }

		    emailSender.send(message);
		    
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
