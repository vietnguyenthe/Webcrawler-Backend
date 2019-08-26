/*
package de.awa.training.webcrawler.controller;

import de.awa.training.webcrawler.repository.KontaktanfrageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;

@RestController
public class EmailController {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private KontaktanfrageRepository kontaktanfrageRepository;


    @RequestMapping("/simpleemail")
    @ResponseBody
    String home() {
        try {
            sendEmail();
            return "Email Sent!";
        }catch(Exception ex) {
            return "Error in sending email: "+ex;
        }
    }

    private void sendEmail() throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo("k.coep@gmx.de");
        helper.setText("How are you?");
        helper.setSubject("Hi");
        helper.setFrom(kontaktanfrageRepository);

        sender.send(message);
    }
}
*/