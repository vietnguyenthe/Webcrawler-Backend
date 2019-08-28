package de.awa.training.webcrawler.controller;

import de.awa.training.webcrawler.entity.UnternehmenEntity;
import de.awa.training.webcrawler.model.Login;
import de.awa.training.webcrawler.repository.UnternehemensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UnternehemensRepository unternehemensRepository;


    @CrossOrigin("http://localhost:3000")
    @PostMapping("/login")
    public String login(@RequestBody Login login) {
        // Login Logik
        // Json Objekt vom Frontend (LoginName und Passwort) annehmen
        // Loginname und passwort mit Datenbank vergleichen (UnternehmenEntits) wenn beide richtig
        // dann return "erfolgreich" sonst "fehlgeschlagen"

        String returnStatement = null;
        String p = login.getLoginPasswort();
        int passwortGehashed = p.hashCode();

        for (UnternehmenEntity unternehmen : unternehemensRepository.findAll()) {
            if (unternehmen.getBenutzername().equals(login.getLoginName()) && unternehmen.getPasswort().equals(passwortGehashed)) {
                returnStatement = "erfolgreich";
                break;
            }
            else {
                returnStatement = "fehlgeschlagen";
            }
        }
        return returnStatement;
    }
}
