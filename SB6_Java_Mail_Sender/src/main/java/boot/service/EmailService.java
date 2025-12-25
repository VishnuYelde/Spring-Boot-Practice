package boot.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String toMail, String subject, String mailBody) {

		SimpleMailMessage sMailMessage = new SimpleMailMessage();
		sMailMessage.setTo(toMail);
		sMailMessage.setSubject(subject);
		sMailMessage.setText(mailBody);

		javaMailSender.send(sMailMessage);

	}

	@Autowired
	private TemplateEngine templateEngine;

	public void sendMailWithTemplete(String toMail, String subject, String username) throws MessagingException {

		MimeMessage mainMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mainMessage, true); // true: Attachments are added with Mail

		Context context = new Context();
		context.setVariable("username", username);

		String templateBody = templateEngine.process("welcome", context);

		helper.setTo(toMail);
		helper.setSubject(subject);
		helper.setText(templateBody, true); // true: Mail body having HTML template

		helper.addAttachment("Resume", new File("C:\\Users\\HP\\Downloads\\Vishnu Resume Nov_25C.pdf"));

		javaMailSender.send(mainMessage);

		System.out.println("Mail sent with Template and attachments");

	}
}
