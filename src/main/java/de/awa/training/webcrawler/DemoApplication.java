package de.awa.training.webcrawler;

import de.awa.training.webcrawler.repository.PfiffiggasRepository;
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

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostConstruct
    private void init() {
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
