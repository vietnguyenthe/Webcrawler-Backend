package de.awa.training.webcrawler.services;

import de.awa.training.webcrawler.model.KontaktAnfrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    private JavaMailSender sender;


    KontaktAnfrage kontaktAnfrage = new KontaktAnfrage();

    // Email an das Flüssiggas Team bei Kontaktanfrage senden mit den nötigen Informationen
    public void mailSendenKontaktanfrage( KontaktAnfrage kontaktAnfrage){
        // Email an das Flüssiggas Team senden mit den nötigen Informationen
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo("TeamFluessiggas@Crawler.com");
            helper.setText(kontaktAnfrage.getNachricht());
            helper.setSubject(kontaktAnfrage.getBetreff());
            helper.setFrom(kontaktAnfrage.getEmailAdresse());

            sender.send(message);

        }catch(Exception ex) {
            System.out.println("Error in sending email: "+ex);
        }
    }

    // Email an das Flüssiggas Team bei Exception senden mit den nötigen Informationen
    public void mailSendenException (String a, String b, String c){
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo("TeamFluessiggas@Crawler.com");
            helper.setText(a);
            helper.setSubject(b);
            helper.setFrom(c); //

            sender.send(message);

        }catch(Exception ex) {
            System.out.println("Error in sending Exeption email: "+ex);
        }
    }

    }




