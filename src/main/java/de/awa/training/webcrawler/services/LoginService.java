package de.awa.training.webcrawler.services;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import de.awa.training.webcrawler.entity.PostleitzahlenEntity;
import de.awa.training.webcrawler.entity.PreiseingabeUnternehmenEntity;
import de.awa.training.webcrawler.entity.UnternehmenEntity;
import de.awa.training.webcrawler.model.PreiseingabeUnternehmen;
import de.awa.training.webcrawler.repository.PLZRepository;
import de.awa.training.webcrawler.repository.PreiseingabeUnternehmenRepository;
import de.awa.training.webcrawler.repository.UnternehemensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;

@Service
public class LoginService {

    @Autowired
    UnternehemensRepository unternehemensRepository;

    @Autowired
    PLZRepository plzRepository;

    @Autowired
    PreiseingabeUnternehmenRepository preiseingabeUnternehmenRepository;

    int aktuellerNutzer;


    public String checkLoginDaten(@RequestBody de.awa.training.webcrawler.model.Login login) {
        // Login Logik
        // Json Objekt vom Frontend (LoginName und Passwort) annehmen und in der Methode checken
        // Loginname und passwort mit Datenbank vergleichen (UnternehmenEntits) wenn beide richtig
        // dann return "erfolgreich" sonst "fehlgeschlagen"
        String returnStatement = null;
        String p = login.getLoginPasswort();
        aktuellerNutzer = p.hashCode();

        for (UnternehmenEntity unternehmen : unternehemensRepository.findAll()) {
            if (unternehmen.getBenutzername().equals(login.getLoginName()) && unternehmen.getPasswort().equals(aktuellerNutzer)) {
                returnStatement = "erfolgreich";
                break;

            }
            else {
                returnStatement = "fehlgeschlagen";
            }
        }
        return returnStatement;
    }


    public void preiseingabeLogin(@RequestBody PreiseingabeUnternehmen preiseingabeUnternehmen) {
        // Voraussetzungen: PreiseingabeUnternehmenEntity erstellt um Daten in Datenbank abspeichern zu können
        PreiseingabeUnternehmenEntity preiseingabeUnternehmenEntity = new PreiseingabeUnternehmenEntity();

        // Dateneingabe gehört zu welchem Unternehmen? Nach UN_Id suchen und festlegen anhand des Passwortes
/*        String f = preiseingabeUnternehmen.getKennwort();
        int m = f.hashCode();*/

        for(UnternehmenEntity k : unternehemensRepository.findAll()){
            if( k.getPasswort().equals(aktuellerNutzer)){
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
                    preiseingabeUnternehmenEntity.setPostleitzahlenId(postleitzahlenEntity.getId());
                }

                // speichern in Datenbank PreisEingabeUnternehmenEntity!
                preiseingabeUnternehmenRepository.save(preiseingabeUnternehmenEntity);
            }
        }
    }





}
