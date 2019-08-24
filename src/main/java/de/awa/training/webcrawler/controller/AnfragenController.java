package de.awa.training.webcrawler.controller;

import de.awa.training.webcrawler.entity.EntityInterface;
import de.awa.training.webcrawler.entity.PostleitzahlenEntity;
import de.awa.training.webcrawler.entity.UnternehmenEntity;
import de.awa.training.webcrawler.model.Daten;
import de.awa.training.webcrawler.model.Anfrage;
import de.awa.training.webcrawler.model.PreisDaten;
import de.awa.training.webcrawler.repository.*;
import de.awa.training.webcrawler.services.AnfragenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
public class AnfragenController {

    @Autowired
    AnfragenService anfragenService;

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/preis/anfrage")
    public List<Daten> anfrageerhalten(@RequestBody Anfrage test){
        Integer plzID = anfragenService.holePLZid(test.getPlz());
        Integer tankgröße = anfragenService.tankgrößeInIndexumwandeln(test.getBehaelter());
        ArrayList<PreisDaten>preisliste = anfragenService.sammlePreise(plzID,tankgröße);
        List<Daten> liste = anfragenService.preisUnternehmenZuweisen(preisliste);
        return liste;
    }


}


      /* System.out.println(test.getBehaelter());
        System.out.println(test.getPlz());*/


        /*liste.add(new Daten("Un","Un","Un","Un","30,00"));
        liste.add(new Daten("AD","AD","AD","AD","32,00"));
        liste.add(new Daten("AD","AD","AD","AD","34,00"));*/
