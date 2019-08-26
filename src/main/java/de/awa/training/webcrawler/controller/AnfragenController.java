package de.awa.training.webcrawler.controller;

import de.awa.training.webcrawler.entity.EntityInterface;
import de.awa.training.webcrawler.entity.KontaktanfrageEntity;
import de.awa.training.webcrawler.entity.PostleitzahlenEntity;
import de.awa.training.webcrawler.entity.UnternehmenEntity;
import de.awa.training.webcrawler.model.Daten;
import de.awa.training.webcrawler.model.Anfrage;
import de.awa.training.webcrawler.model.KontaktAnfrage;
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

    @Autowired
    KontaktanfrageRepository kontaktanfrageRepository;

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/preis/anfrage")
    public List<Daten> anfrageerhalten(@RequestBody Anfrage test){
        String behaelter = test.getBehaelter();
        Integer plzID = anfragenService.holePLZid(test.getPlz());
        Integer tankgröße = anfragenService.tankgrößeInIndexumwandeln(behaelter);
        ArrayList<PreisDaten>preisliste = anfragenService.sammlePreise(plzID,tankgröße);
        List<Daten> liste = anfragenService.preisUnternehmenZuweisen(preisliste);
        return liste;
    }

    @PostMapping("/kontaktUnternehmen")
    public void unternehmensAnfrage (@RequestBody KontaktanfrageEntity kontaktAnfrage){
        System.out.println(kontaktAnfrage.getEmailAdresse());
        System.out.println(kontaktAnfrage.getBetreff());
        System.out.println(kontaktAnfrage.getKontaktperson());

/*        KontaktanfrageEntity kontaktanfrageEntity = new KontaktanfrageEntity();
        kontaktanfrageEntity.setBetreff(kontaktAnfrage.getBetreff());
        kontaktanfrageEntity.setFirmennamen(kontaktAnfrage.getFirmennamen());
        kontaktanfrageEntity.setFirmenadresse(kontaktAnfrage.getFirmenadresse());
        kontaktanfrageEntity.setPlz(kontaktAnfrage.getPlz());
        kontaktanfrageEntity.setKontaktperson(kontaktAnfrage.getKontaktperson());
        kontaktanfrageEntity.setEmailAdresse(kontaktAnfrage.getEmailAdresse());
        kontaktanfrageEntity.setNachricht(kontaktAnfrage.getNachricht());*/

        kontaktanfrageRepository.save(kontaktAnfrage);
}



}


      /* System.out.println(test.getBehaelter());
        System.out.println(test.getPlz());*/


        /*liste.add(new Daten("Un","Un","Un","Un","30,00"));
        liste.add(new Daten("AD","AD","AD","AD","32,00"));
        liste.add(new Daten("AD","AD","AD","AD","34,00"));*/
