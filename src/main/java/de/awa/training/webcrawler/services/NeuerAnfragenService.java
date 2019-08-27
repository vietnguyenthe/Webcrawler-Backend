package de.awa.training.webcrawler.services;

import de.awa.training.webcrawler.entity.EntityInterface;
import de.awa.training.webcrawler.entity.PostleitzahlenEntity;
import de.awa.training.webcrawler.entity.PreiseingabeUnternehmenEntity;
import de.awa.training.webcrawler.entity.UnternehmenEntity;
import de.awa.training.webcrawler.model.Daten;
import de.awa.training.webcrawler.model.PreisDaten;
import de.awa.training.webcrawler.model.PreiseingabeUnternehmen;
import de.awa.training.webcrawler.repository.PLZRepository;
import de.awa.training.webcrawler.repository.PreiseingabeUnternehmenRepository;
import de.awa.training.webcrawler.repository.UnternehemensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



@Service
public class NeuerAnfragenService {

    @Autowired
    PLZRepository plzRepository;

    @Autowired
    UnternehemensRepository unternehemensRepository;

    @Autowired
    PreiseingabeUnternehmenRepository preiseingabeUnternehmenRepository;

    public Integer holePLZid(String plz) {
        Integer gesuchtePLZid = null;
        List<PostleitzahlenEntity> plzListe = plzRepository.findAll();
        for (PostleitzahlenEntity entity : plzListe) {
            if (entity.getPlz().equals(plz)) {
                gesuchtePLZid = entity.getId();
                return gesuchtePLZid;
            }
        }
        return null;
    }

    public Integer tankgrößeInIndexumwandeln(String behaelter){
        if(behaelter.equals("preis2700liter")){
            return 0;
        }else if(behaelter.equals("preis4850liter")){
            return 1;
        }else if(behaelter.equals("preis6400liter")){
            return 2;
        }
        return null;
    }

    public ArrayList<Daten> unternehmensDatenGenerieren() {
        List<UnternehmenEntity> listeUnternehmen = unternehemensRepository.findAll();
        ArrayList<Daten> liste = new ArrayList<>();
        for (UnternehmenEntity entity : unternehemensRepository.findAll()) {
            liste.add(new Daten(String.valueOf(entity.getId()),entity.getName(), entity.getAdresse(), entity.getPlz(), entity.getOrt()));
        }
        return liste;
    }

    public List<PreisDaten> sammlePreise(Integer plz, Integer tankgröße ){
        List<PreisDaten> preisliste = new ArrayList<>();
        List<Daten>unternehmensliste = unternehmensDatenGenerieren();
        for(int x =1;x<=unternehmensliste.size();x++){
            preisliste.add(new PreisDaten(x,holePreisausEntitytabelle(preiseingabeUnternehmenRepository,x,plz,tankgröße)));
        }
       return preisliste;
    }

    public String holePreisausEntitytabelle(JpaRepository repository, Integer id, Integer plzID, Integer tankgröße){
        List<EntityInterface> sortierteListe = sortiereAbsteigend(repository);
        List<String> leereListe = new ArrayList<>();
            for (EntityInterface entity : sortierteListe) {
                if (entity.getPostleitzahlenId().equals(plzID)&& id.equals(entity.getId())) {
                    leereListe.add(entity.getPreis2700Liter());
                    leereListe.add(entity.getPreis4850Liter());
                    leereListe.add(entity.getPreis6400Liter());
                    return leereListe.get(tankgröße);
                }
            }
        return "Kein Wert für die PLZ gefunden";
    }


    public List<EntityInterface> sortiereAbsteigend(JpaRepository repository){
        List<EntityInterface> liste = repository.findAll();
        Collections.sort(liste,Collections.reverseOrder());
        return liste;
    }

    public List<Daten> preisUnternehmenZuweisen(List<PreisDaten> preisliste){
        ArrayList<Daten>neueUnternehmensliste = unternehmensDatenGenerieren();
        for (Daten unternehmen:neueUnternehmensliste) {
            for (PreisDaten preis:preisliste){
                if(unternehmen.getId().equals(String.valueOf(preis.getId()))){
                    unternehmen.setPreis(preis.getPreis());
                }
            }
        }
        // preissortierung
        return neueUnternehmensliste;
    }



}
