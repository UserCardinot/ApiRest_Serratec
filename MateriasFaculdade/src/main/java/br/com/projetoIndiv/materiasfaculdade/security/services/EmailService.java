package br.com.projetoIndiv.materiasfaculdade.security.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailService {	
	@Autowired
	public JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String emailFrom;

	@Value("${spring.mail.host}")
	private String emailHost;

	@Value("${spring.mail.port}")
	private Integer emailPort;
	
	@Value("${spring.mail.password}")
	private String emailPassword;

	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setUsername(emailFrom);
		mailSender.setHost(emailHost);
		mailSender.setPort(emailPort);
		mailSender.setPassword(emailPassword);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		return mailSender;
	}

	public String writerEmail(){
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("debsdebbie90@gmail.com");
		message.setSubject("Teste de envio de email");
		message.setText("Email enviado com sucesso! " + time.format(format));
		message.setFrom("lucascardinot2000@gmail.com");

		try {
			javaMailSender.send(message);
			return "Email enviado com sucesso";
		} catch (Exception e) {
			return "Erro ao enviar email\n\n" + e.getMessage();
		}
	}

	public String writerEmail2(){
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setSubject("Teste de envio de email 2");
			helper.setTo("debsdebbie90@gmail.com");
			helper.setFrom("lucascardinot2000@gmail.com");

			String html = "<h1>ME FAZ UM PIX AE</h1><br><p>Email enviado com sucesso! " + time.format(format) + "</p>";

			helper.setText(html,true);
			javaMailSender.send(message);
			return "Email enviado com sucesso";
		} catch (MessagingException e) {
			return "Erro ao enviar email\n\n" + e.getMessage();
		}
	}
}