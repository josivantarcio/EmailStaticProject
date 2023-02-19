package br.com.emailproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.emailproject.model.Email;
import br.com.emailproject.util.LogUtil;

@Stateless
public class EmailService extends Thread {
	
	private List<Email> emails;
	
	public static final String HEADER_CONTEXT = "text/html; charset=utf-8";
	
	public void enviar(Email email) {
		emails = new ArrayList<>();
		emails.add(email);
		send();
	}
	
	public void enviar(List<Email> emails) {
		this.emails = emails;
		send();
	}
	
	private EmailService copy(){
		EmailService emailService = new EmailService();
		emailService.emails = emails;
		return emailService;
	}

	private void send() {
		new Thread(this.copy()).start();
	}
	
	@Override
	public void run() {
		Properties property = new Properties();
		
		property.put("email.smtp.starttls.enable", true);
		property.put("email.smtp.host", System.getProperty("email-project.mail.smtp.host"));
		property.put("email.smtp.port", System.getProperty("email-project.mail.smtp.port"));
		
		Session session = Session.getInstance(property);
		session.setDebug(false);
		
		for (Email email : emails) {
			try {
				Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(System.getProperty("email-project.mail.from")));
			
			if(email.getDestino().contains("/")) {
				List<InternetAddress> emailsDestinatarios = new ArrayList<>();
				for(String e : email.getDestino().split("/")) {
					emailsDestinatarios.add(new InternetAddress(e));
				}
				message.addRecipients(Message.RecipientType.TO, emailsDestinatarios.toArray(new InternetAddress[0]));
			}else {
				InternetAddress to = new InternetAddress(email.getDestino());
				message.addRecipient(Message.RecipientType.TO, to);
			}
			
			message.setSubject(email.getAssunto());
			MimeBodyPart texPart = new MimeBodyPart();
			texPart.setHeader("Content-Type", HEADER_CONTEXT);
			texPart.setContent(email.getTexto(), HEADER_CONTEXT);
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(texPart);
			
			message.setContent(multipart);
			
			Transport.send(message);
			
			}catch(MessagingException e) {
				LogUtil.getLogger(EmailService.class).error("Error ao enviar email" + e.getMessage());
			}
		}
	}
}
