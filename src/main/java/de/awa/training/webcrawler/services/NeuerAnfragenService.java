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


    // sucht für die übergebene Postleitzahl die zugehörige ID in der Datenbank.
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

    //Wandelt die Behältereingabe in Indexwerte um. Damit diese in anderen Methoden verwendet werden.
    public Integer tankgrößeInIndexumwandeln(String behaelter) {
        if (behaelter.equals("preis2700liter")) {
            return 0;
        } else if (behaelter.equals("preis4850liter")) {
            return 1;
        } else if (behaelter.equals("preis6400liter")) {
            return 2;
        }
        return null;
    }

    //generiert eine Model Liste mit Datenobjekten, bei denen nur die Eigenschafft Preis nicht gesetzt ist.
    public ArrayList<Daten> unternehmensDatenGenerieren() {
        List<UnternehmenEntity> listeUnternehmen = unternehemensRepository.findAll();
        ArrayList<Daten> liste = new ArrayList<>();
        for (UnternehmenEntity entity : unternehemensRepository.findAll()) {
            liste.add(new Daten(String.valueOf(entity.getId()), entity.getName(), entity.getAdresse(), entity.getPlz(), entity.getOrt()));
        }
        return liste;
    }

    //sammelt die richtigen Preise für die Postleitzahl und die Tankgröße und speichert sie in einer preisListe mit Unternehmen ID
    public List<PreisDaten> sammlePreise(Integer plz, Integer tankgröße) {
        List<PreisDaten> preisliste = new ArrayList<>();
       // List<Daten> unternehmensliste = unternehmensDatenGenerieren();
        for (int x = 1; x <= unternehemensRepository.findAll().size(); x++) {
            preisliste.add(new PreisDaten(x, holePreisausEntitytabelle(preiseingabeUnternehmenRepository, plz, x, tankgröße)));
        }
        return preisliste;
    }
   //gibt den richtigen Preis für die Parameter als String zurück.
    public String holePreisausEntitytabelle(JpaRepository repository, Integer plz, Integer id, Integer tankgröße) {
        List<EntityInterface> allePreise = sortiereAbsteigend(repository);
        List<String> leereListe = new ArrayList<>();
        for (EntityInterface entity : allePreise) {
            if (entity.getPostleitzahlenId().equals(plz) && entity.getUnternehmenId().equals(id)) {
                leereListe.add(entity.getPreis2700Liter());
                leereListe.add(entity.getPreis4850Liter());
                leereListe.add(entity.getPreis6400Liter());
                return leereListe.get(tankgröße);
            }
        }
        return "Kein Preis gefunden";
    }
    // weisst der Liste mit Unternehmensdaten Objekten anhand der Preisliste die richtigen Preis zu.
    public List<Daten> preisUnternehmenZuweisen(List<PreisDaten> preisliste){
        List<Daten>neueUnternehmensliste = unternehmensDatenGenerieren();
        for (Daten unternehmen:neueUnternehmensliste) {
            for (PreisDaten preis:preisliste){
                if(unternehmen.getId().equals(String.valueOf(preis.getId()))){
                    unternehmen.setPreis(preis.getPreis());
                }
            }
        }
        neueUnternehmensliste = sortierePreise(neueUnternehmensliste);
        return neueUnternehmensliste;
    }

    // sortiert die Daten Liste nach dem Attribut Preise aufsteigend
    public List <Daten> sortierePreise(List <Daten> neuUnternehmensliste){
        Collections.sort(neuUnternehmensliste);
        return neuUnternehmensliste;
    }

    //sortiert die ID absteigend
    public List<EntityInterface> sortiereAbsteigend(JpaRepository repository){
        List<EntityInterface> liste = repository.findAll();
        Collections.sort(liste,Collections.reverseOrder());
        return liste;
    }

    //filtert die Unternehmen heraus die keine Preise anbieten für die Postleitzahl
    public List<Daten> filtereUnternehmenOhnePreis(List<Daten> liste){
        List<Daten> gefilterteListe = new ArrayList<>();
        for(Daten daten:liste){
            if(!daten.getPreis().equals("Kein Preis gefunden")){
                gefilterteListe.add(daten);
            }
        }
        return gefilterteListe;
    }


}


