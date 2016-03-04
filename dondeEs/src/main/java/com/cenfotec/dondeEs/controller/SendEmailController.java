package com.cenfotec.dondeEs.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.QueryParam;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.dondeEs.pojo.ListSimplePOJO;
@RestController
@RequestMapping(value = "rest/protected/sendEmail")
public class SendEmailController {

	private static final String from = "evaluacionescenfotec@gmail.com";
	private static final String host = "smtp.gmail.com";
	private static final String pass = "evaluacionescenfotec2015";
	private static String subject;
	private static String text;
	private static Properties props;
	
	public static void generalEmail(){
		 props = System.getProperties();

		props.put("mail.smtp.starttls.enable", "true");

		props.put("mail.smtp.ssl.trust", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

	}
	
	@RequestMapping(value = "/sendEmailInvitation", method = RequestMethod.POST)
	public void sendEmailInvitation(@RequestBody ListSimplePOJO to, @QueryParam("eventId") int eventId) {
				
		generalEmail();
		Session session = Session.getDefaultInstance(props);
		subject = "Invitacion a un evento";
		try {

			
			Transport transport = session.getTransport("smtp");

			// To get the array of addresses
			for (String email : to.getListSimple()) {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				
				text = "http://localhost:8080/dondeEs/app#/answerInvitation?eventId="+ eventId+"&email="+email;
				
				InternetAddress internetAddress = new InternetAddress(email);
				message.addRecipient(Message.RecipientType.TO, internetAddress);
				message.setSubject(subject);
				message.setText(text);
				transport.connect(host, from, pass);
				transport.sendMessage(message, message.getAllRecipients());

			}
			transport.close();

			
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}
	


}
