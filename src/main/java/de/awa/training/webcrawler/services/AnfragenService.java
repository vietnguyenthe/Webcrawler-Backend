package de.awa.training.webcrawler.services;

import de.awa.training.webcrawler.entity.EntityInterface;
import de.awa.training.webcrawler.entity.PostleitzahlenEntity;
import de.awa.training.webcrawler.entity.UnternehmenEntity;
import de.awa.training.webcrawler.model.Daten;
import de.awa.training.webcrawler.model.PreisDaten;
import de.awa.training.webcrawler.repository.*;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AnfragenService {

    @Autowired
    UnternehemensRepository unternehemensRepository;

    @Autowired
    PLZRepository plzRepository;

    @Autowired
    MeinFluessiggasRepository meinFluessiggasRepository;

    @Autowired
    PfiffiggasRepository pfiffiggasRepository;

    @Autowired
    Fluessiggas123Repository fluessiggas123Repository;

    @Autowired
    TestRespository testRespository;

    @Autowired
    BeispielRepository beispielRepository;


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

    public String holePreisausEntitytabelle(JpaRepository repository, Integer plzID, Integer tankgröße){
        List<EntityInterface> sortierteListe = sortiereAbsteigend(repository);
        List<String> leereListe = new ArrayList<>();
        for (EntityInterface entity:sortierteListe){
            if(entity.getPostleitzahlenId().equals(plzID)){
                leereListe.add(entity.getPreis2700Liter());
                leereListe.add(entity.getPreis4850Liter());
                leereListe.add(entity.getPreis6400Liter());
                return leereListe.get(tankgröße);
            }
        }
        return "Kein Wert für die PLZ gefunden";
    }

    public ArrayList<PreisDaten>sammlePreise(Integer plzID, Integer tankgröße){
        ArrayList<PreisDaten> preisliste = new ArrayList<>();
        preisliste.add(new PreisDaten(3,holePreisausEntitytabelle(fluessiggas123Repository,plzID,tankgröße)));
        preisliste.add(new PreisDaten(2,holePreisausEntitytabelle(meinFluessiggasRepository,plzID,tankgröße)));
        preisliste.add(new PreisDaten(1,holePreisausEntitytabelle(pfiffiggasRepository,plzID,tankgröße)));
        preisliste.add(new PreisDaten(5,holePreisausEntitytabelle(testRespository,plzID,tankgröße)));
        preisliste.add(new PreisDaten(4,holePreisausEntitytabelle(beispielRepository,plzID,tankgröße)));
        return preisliste;
    }


    public ArrayList<Daten>unternehmensDatenGenerieren() {
        List<UnternehmenEntity> listeUnternehmen = unternehemensRepository.findAll();
        ArrayList<Daten> liste = new ArrayList<>();
        for (UnternehmenEntity entity : unternehemensRepository.findAll()) {
            liste.add(new Daten(String.valueOf(entity.getId()),entity.getName(), entity.getAdresse(), entity.getPlz(), entity.getOrt()));
        }
        return liste;
    }


    public List<Daten> preisUnternehmenZuweisen(ArrayList<PreisDaten> preisliste){
        List<Daten>neueUnternehmensliste = unternehmensDatenGenerieren();
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

    public List<EntityInterface> sortiereAbsteigend(JpaRepository repository){
        List<EntityInterface> liste = repository.findAll();
        Collections.sort(liste,Collections.reverseOrder());
        return liste;
    }

    public ArrayList <Daten> sortierePreise(ArrayList <Daten> neuUnternehmensliste){
        Collections.sort(neuUnternehmensliste);
        return neuUnternehmensliste;
    }


}
