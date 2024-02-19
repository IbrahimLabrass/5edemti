package com.freelance.pfe.security.services;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.freelance.pfe.model.Email;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }
    
    public void sendmail(Email email) throws MailException, AddressException, MessagingException, IOException {
    	   Properties props = new Properties();
    	   props.put("mail.smtp.auth", "true");
    	   props.put("mail.smtp.starttls.enable", "true");
    	   props.put("mail.smtp.host", "smtp.gmail.com");
    	   props.put("mail.smtp.port", "587");
    	   
    	   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
    	      protected PasswordAuthentication getPasswordAuthentication() {
    	         return new PasswordAuthentication("dhyaakoubi@gmail.com", "24028987");
    	      }
    	   });
    	   Message msg = new MimeMessage(session);
    	   msg.setFrom(new InternetAddress(email.getEmail(), email.getName()));

    	   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("shoot69mee@gmail.com"));
    	   msg.setSubject(email.getSubject());
    	   msg.setContent(email.getComment(), "text/html");
    	   //msg.setFrom(email.getName());
    	   //msg.setFrom(email.getEmail(),email.getName());
    	   msg.setSentDate(new Date());

    	   MimeBodyPart messageBodyPart = new MimeBodyPart();
    	   messageBodyPart.setContent(email.getComment(), "text/html");

    	   Multipart multipart = new MimeMultipart();
    	   multipart.addBodyPart(messageBodyPart);
    	   
    	   msg.setContent(multipart);
    	   Transport.send(msg);   
    	}
    
    /*
    public void sendEmail() throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("canavaro.2009@gmail.com");
        mail.setFrom("mohamedyaakoubiweb@gmail.com");
        mail.setSubject("Contacto: "+"ahmed");
        mail.setText("hello");
        javaMailSender.send(mail);
    }
    */
}
