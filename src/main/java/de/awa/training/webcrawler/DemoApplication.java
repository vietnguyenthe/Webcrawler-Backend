package de.awa.training.webcrawler;

import de.awa.training.webcrawler.entity.PostleitzahlenEntity;
import de.awa.training.webcrawler.entity.UnternehmenEntity;
import de.awa.training.webcrawler.repository.PLZRepository;
import de.awa.training.webcrawler.repository.UnternehemensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.awa.training.webcrawler.services.Fluessiggas123;
import de.awa.training.webcrawler.services.MeinFluessiggasWebcrawler;
import de.awa.training.webcrawler.services.PfiffiggasWebcrawler;

import java.text.ParseException;
import org.openqa.selenium.NoSuchElementException;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class DemoApplication {

    @Autowired
    PfiffiggasWebcrawler pfiffiggasWebcrawler;

    @Autowired
    MeinFluessiggasWebcrawler meinFluessiggasWebcrawler;

    @Autowired
    Fluessiggas123 fluessiggas123;

    @Autowired
    PLZRepository plzRepository;

    @Autowired
    UnternehemensRepository unternehemensRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostConstruct
    private void init() {
        String[]postleitzahlen = {"51570","52152","53804", "54290","55543","56072","57539","58119"};
        String [][]unternehmen = {
                {"Propan Rheingas GmbH & Co. KG","Fischenicher Straße 23","50321","Brühl"},
                {"DFG Deutsche Flüssiggas GmbH","Stau 169","262122","Oldenburg"},
                {"TEGA - Technische Gase und Gasetechnik GmbH", "Blumenstraße 70","99092", "Erfurt"}
        };


        if(plzRepository.findAll().isEmpty()) {
            for (String plz : postleitzahlen) {
                PostleitzahlenEntity entity = new PostleitzahlenEntity();
                entity.setPlz(plz);
                plzRepository.save(entity);
            }
        }

        if(unternehemensRepository.findAll().isEmpty()){
            for (String[]einezelnesUnternehmen:unternehmen) {
                    UnternehmenEntity entity = new UnternehmenEntity();
                    entity.setName(einezelnesUnternehmen[0]);
                    entity.setAdresse(einezelnesUnternehmen[1]);
                    entity.setPlz(einezelnesUnternehmen[2]);
                    entity.setOrt(einezelnesUnternehmen[3]);
                    unternehemensRepository.save(entity);
                }
            }

        try {
            pfiffiggasWebcrawler.allePLZCrawlen();
            //meinFluessiggasWebcrawler.allePLZCrawlen();
            //fluessiggas123.allePLZCrawlen();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("Fehler! Crawler nicht erfolgreich");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
