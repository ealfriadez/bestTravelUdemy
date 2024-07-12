package pe.edu.unfv.besttraveludemy.infraestructure.helper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.Paths;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class EmailHelper {

	private final JavaMailSender mailSender;
	
	public void sendMail(String to, String name, String product) {
		MimeMessage message = mailSender.createMimeMessage();
		String htmlContent = "";
		
		try {
			message.setFrom("ealfriadez@gmail.com");
			message.setRecipients(MimeMessage.RecipientType.TO, to);
			message.setContent(htmlContent, "text/html; charset=utf-8");
			mailSender.send(message);
		} catch (MessagingException e) {
			log.error("Error to send mail", e);
		}
	}
	
	private String readHTMLTemplate(String name, String product) {
		try(var lines = Files.lines(TEMPLATE_PATH)) {
			var html = lines.collect(Collectors.joining());
			
			return html.replace("{name}", name).replace("{product}", product);
		} 
	}
	
	private final Path TEMPLATE_PATH1 = Paths.get("src/main/resources/email/email_template.html");
}
