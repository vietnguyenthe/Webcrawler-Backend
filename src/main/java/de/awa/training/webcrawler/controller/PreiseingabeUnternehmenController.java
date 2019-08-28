package de.awa.training.webcrawler.controller;

import de.awa.training.webcrawler.entity.PostleitzahlenEntity;
import de.awa.training.webcrawler.entity.PreiseingabeUnternehmenEntity;
import de.awa.training.webcrawler.entity.UnternehmenEntity;
import de.awa.training.webcrawler.model.PreiseingabeUnternehmen;
import de.awa.training.webcrawler.repository.PLZRepository;
import de.awa.training.webcrawler.repository.PreiseingabeUnternehmenRepository;
import de.awa.training.webcrawler.repository.UnternehemensRepository;
import de.awa.training.webcrawler.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
public class PreiseingabeUnternehmenController {

    @Autowired
    PreiseingabeUnternehmenRepository preiseingabeUnternehmenRepository;


    @Autowired
    UnternehemensRepository unternehemensRepository;

    @Autowired
    PLZRepository plzRepository;

    @Autowired
    LoginService loginService;


    @CrossOrigin("http://localhost:3000")
    @PostMapping("/preiseingabe")
    public void preiseingabe(@RequestBody PreiseingabeUnternehmen preiseingabeUnternehmen) {
        loginService.preiseingabeLogin(preiseingabeUnternehmen);
    }
}