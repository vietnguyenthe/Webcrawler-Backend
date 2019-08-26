package de.awa.training.webcrawler.controller;

import de.awa.training.webcrawler.entity.PreiseingabeUnternehmenEntity;
import de.awa.training.webcrawler.model.PreiseingabeUnternehmen;
import de.awa.training.webcrawler.repository.PreiseingabeUnternehmenRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PreiseingabeUnternehmenController {

    // Unternehmensanfrage aus dem Frontend entgegennehmen und in der Datenbank speichern
    @PostMapping("/kontaktUnternehmen")
    public void unternehmensAnfrage (@RequestBody KontaktAnfrage kontaktAnfrage){

        // Entity Kontaktanfrage erstellen und dann die erhaltenen Daten mit dem Setter und getter festlegen + speichern
        KontaktanfrageEntity kontaktanfrageEntity = new KontaktanfrageEntity();

        kontaktanfrageEntity.setBetreff(kontaktAnfrage.getBetreff());
        kontaktanfrageEntity.setFirmennamen(kontaktAnfrage.getFirmennamen());
        kontaktanfrageEntity.setFirmenadresse(kontaktAnfrage.getFirmenadresse());
        kontaktanfrageEntity.setOrt(kontaktAnfrage.getOrt());
        kontaktanfrageEntity.setPlz(kontaktAnfrage.getPlz());
        kontaktanfrageEntity.setKontaktperson(kontaktAnfrage.getKontaktperson());
        kontaktanfrageEntity.setEmailAdresse(kontaktAnfrage.getEmailAdresse());
        kontaktanfrageEntity.setNachricht(kontaktAnfrage.getNachricht());

        kontaktanfrageRepository.save(kontaktanfrageEntity);
    }

    @PostMapping("/preiseingabe")
    public void preiseingabe(@RequestBody PreiseingabeUnternehmen preiseingabeUnternehmen){

        PreiseingabeUnternehmenEntity preiseingabeUnternehmenEntity = new PreiseingabeUnternehmenEntity();

        PreiseingabeUnternehmenRepository.save(preiseingabeUnternehmenEntity);
    }
}
