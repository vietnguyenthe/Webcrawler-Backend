package de.awa.training.webcrawler.controller;

import de.awa.training.webcrawler.model.Test;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnfragenController {

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/preis/anfrage")
    public String anfrageerhalten(@RequestBody Test test){
        System.out.println(test.getBehaelter());
        System.out.println(test.getPlz());
        return "Hallo";
    }

}
