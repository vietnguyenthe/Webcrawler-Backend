package de.awa.training.webcrawler.controller;

import de.awa.training.webcrawler.entity.PostleitzahlenEntity;
import de.awa.training.webcrawler.entity.PreiseingabeUnternehmenEntity;
import de.awa.training.webcrawler.entity.UnternehmenEntity;
import de.awa.training.webcrawler.model.PreiseingabeUnternehmen;
import de.awa.training.webcrawler.repository.PLZRepository;
import de.awa.training.webcrawler.repository.PreiseingabeUnternehmenRepository;
import de.awa.training.webcrawler.repository.UnternehemensRepository;
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


    @CrossOrigin("http://localhost:3000")
    @PostMapping("/preiseingabe")
    public void preiseingabe(@RequestBody PreiseingabeUnternehmen preiseingabeUnternehmen) {

        // Voraussetzungen
        PreiseingabeUnternehmenEntity preiseingabeUnternehmenEntity = new PreiseingabeUnternehmenEntity();

        // Dateneingabe gehört zu welchem Unternehmen? Nach UN_Id suchen und festlegen anhand des Passwortes
        String f = preiseingabeUnternehmen.getKennwort();
        int m = f.hashCode();

        for(UnternehmenEntity k : unternehemensRepository.findAll()){
            if( k.getPasswort().equals(m)){
                preiseingabeUnternehmenEntity.setUnternehmenId(k.getId());

                // Danach Json Daten vom Frontend in PreisEingabeUnternehmenEntity(Datenbank) speichern
                preiseingabeUnternehmenEntity.setDatum(new Date(new java.util.Date().getTime()));
                preiseingabeUnternehmenEntity.setPreis2700Liter(preiseingabeUnternehmen.getPreis2700Liter());
                preiseingabeUnternehmenEntity.setPreis4850Liter(preiseingabeUnternehmen.getPreis4850Liter());
                preiseingabeUnternehmenEntity.setPreis6400Liter(preiseingabeUnternehmen.getPreis6400Liter());

                // zusätzlich noch die restlichen fehlenden Attribute für PreisEingabeUnternehmentEntity festlegen wie PlzId
                boolean plzEnthalten = false;
                int plz = 0;
                for(PostleitzahlenEntity i: plzRepository.findAll()){
                    if(preiseingabeUnternehmen.getPostleitzahl().equals(i.getPlz())){
                       plzEnthalten = true;
                       plz = i.getId();
                    }
                }

                if(plzEnthalten == true){
                    preiseingabeUnternehmenEntity.setPostleitzahlenId(plz);
                } else {
                    PostleitzahlenEntity postleitzahlenEntity = new PostleitzahlenEntity();
                    postleitzahlenEntity.setPlz(preiseingabeUnternehmen.getPostleitzahl());
                    plzRepository.save(postleitzahlenEntity);
                }

/*                preiseingabeUnternehmenEntity.setPostleitzahlenId(i.getId());
                System.out.println("Richtige Plz gefunden: " +  i.getPlz());

                */

                // speichern in Datenbank PreisEingabeUnternehmenEntity!
                preiseingabeUnternehmenRepository.save(preiseingabeUnternehmenEntity);
            }

        }


/*      else if (!plzRepository.findAll().contains(i.getPlz())){
            PostleitzahlenEntity postleitzahlenEntity = new PostleitzahlenEntity();
            postleitzahlenEntity.setPlz(preiseingabeUnternehmen.getPostleitzahl());
            plzRepository.save(postleitzahlenEntity);
        }*/

//        i.getPlz().equals(preiseingabeUnternehmen.getPostleitzahl())



/*        PreiseingabeUnternehmenEntity preiseingabeUnternehmenEntity = new PreiseingabeUnternehmenEntity();
        UnternehmenEntity unternehmenEntity = new UnternehmenEntity();

        preiseingabeUnternehmenEntity.setPreis2700Liter(preiseingabeUnternehmen.getPreis2700Liter());
        preiseingabeUnternehmenEntity.setPreis4850Liter(preiseingabeUnternehmen.getPreis4850Liter());
        preiseingabeUnternehmenEntity.setPreis6400Liter(preiseingabeUnternehmen.getPreis6400Liter());
        preiseingabeUnternehmenEntity.setDatum(new Date(new java.util.Date().getTime()));
        preiseingabeUnternehmenEntity.setId(unternehmenEntity.getId());*/

/*
        unternehmenEntity.setAdresse(preiseingabeUnternehmen.getAdresse());
        unternehmenEntity.setName(preiseingabeUnternehmen.getName());
        unternehmenEntity.setOrt(preiseingabeUnternehmen.getOrt());
        unternehmenEntity.setPlz(preiseingabeUnternehmen.getPlz());
*/
/*
        preiseingabeUnternehmenRepository.save(preiseingabeUnternehmenEntity);
        unternehemensRepository.save(unternehmenEntity);*/
    }
}
