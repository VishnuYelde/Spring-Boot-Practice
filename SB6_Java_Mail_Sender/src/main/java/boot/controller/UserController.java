package boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boot.service.EmailService;
import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/mail")
public class UserController {
	
	@Autowired
	private EmailService emailService;

	@GetMapping("/smail")
	public String simpleMailSend(@RequestParam String toMail) {
		String subject = "Congratulations! You are Selected for Java Developer Role";
		String mailBody = "Dear Vishnu, we are pleased to inform you that you have been selected for the Java Developer position following your recent interviews; "
				+ "we were very impressed with your skills and experience, and we believe you will be a valuable addition to our team. "
				+ "Please find the formal offer letter and details regarding the next steps of your onboarding process attached to this email,"
				+ " and we look forward to receiving your response by";
		
		emailService.sendMail(toMail, subject, mailBody);
		return "Mail Sent...!";
	}
	
	@GetMapping("/mailtemp")
	public String sendMailTemplete(@RequestParam String toMail, @RequestParam String username) throws MessagingException {
		String subject = "Congratulations!, You are Hired";
		emailService.sendMailWithTemplete(toMail, subject, username);
		
		return "Mail sent with Template and attachments";
	}
	

}
