package com.freelance.pfe.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;
import com.freelance.pfe.model.Email;
import com.freelance.pfe.security.services.EmailService;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class EmailController {

	
    @Autowired
    private JavaMailSender sender;
    @Autowired
    private EmailService emailService;

    @RequestMapping("/getemail")
    //public @ResponseBody Email sendMail(@RequestBody Email email) throws Exception {
    public String sendEmail(@RequestBody Email email) throws AddressException, MessagingException, IOException {
    	emailService.sendmail(email);
    	   return "Email sent successfully";   
    	
    	/*
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name",email.getName());
        model.put("email",email.getEmail());
        model.put("subject",email.getSubject());
        model.put("comment",email.getComment());

        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process("email-template", context);

        try {
            helper.setTo(email.getEmail());
            helper.setText(email.getComment(),true);
            helper.setSubject("Test Mail");
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);

        return email;
        */
    }
}
