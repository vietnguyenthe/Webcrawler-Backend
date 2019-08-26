package de.awa.training.webcrawler.controller;

import de.awa.training.webcrawler.model.Login;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/login")
    public void login(@RequestBody Login login){
        System.out.println(login.getLoginName());
        System.out.println(login.getLoginPasswort());

    }



}
