package de.awa.training.webcrawler.controller;

import de.awa.training.webcrawler.entity.PreiseingabeUnternehmenEntity;
import de.awa.training.webcrawler.entity.UnternehmenEntity;
import de.awa.training.webcrawler.model.PreiseingabeUnternehmen;
import de.awa.training.webcrawler.repository.PreiseingabeUnternehmenRepository;
import de.awa.training.webcrawler.repository.UnternehemensRepository;
import de.awa.training.webcrawler.services.AnfragenService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.GeneratedValue;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
public class PreiseingabeUnternehmenController {

    @Autowired
    PreiseingabeUnternehmenRepository preiseingabeUnternehmenRepository;

    @Autowired
    AnfragenService anfragenService;

    @Autowired
    UnternehemensRepository unternehemensRepository;

    @PostMapping("/preiseingabe")
    public void preiseingabe(@RequestBody PreiseingabeUnternehmen preiseingabeUnternehmen) {

        PreiseingabeUnternehmenEntity preiseingabeUnternehmenEntity = new PreiseingabeUnternehmenEntity();
        UnternehmenEntity unternehmenEntity = new UnternehmenEntity();

        preiseingabeUnternehmenEntity.setPreis2700Liter(preiseingabeUnternehmen.getPreis2700Liter());
        preiseingabeUnternehmenEntity.setPreis4850Liter(preiseingabeUnternehmen.getPreis4850Liter());
        preiseingabeUnternehmenEntity.setPreis6400Liter(preiseingabeUnternehmen.getPreis6400Liter());
        preiseingabeUnternehmenEntity.setDatum(new Date(new java.util.Date().getTime()));
        preiseingabeUnternehmenEntity.setId(unternehmenEntity.getId());

/*
        unternehmenEntity.setAdresse(preiseingabeUnternehmen.getAdresse());
        unternehmenEntity.setName(preiseingabeUnternehmen.getName());
        unternehmenEntity.setOrt(preiseingabeUnternehmen.getOrt());
        unternehmenEntity.setPlz(preiseingabeUnternehmen.getPlz());
*/

        preiseingabeUnternehmenRepository.save(preiseingabeUnternehmenEntity);
        //unternehemensRepository.save(unternehmenEntity);
    }
}
