package de.awa.training.webcrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.awa.training.webcrawler.services.Fluessiggas123;
import de.awa.training.webcrawler.services.MeinFluessiggasWebcrawler;
import de.awa.training.webcrawler.services.PfiffiggasWebcrawler;

import java.text.ParseException;
import org.openqa.selenium.NoSuchElementException;


@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {


        SpringApplication.run(DemoApplication.class, args);
        PfiffiggasWebcrawler pfiffiggasWebcrawler = new PfiffiggasWebcrawler();
        MeinFluessiggasWebcrawler meinFluessiggasWebcrawler = new MeinFluessiggasWebcrawler();
        Fluessiggas123 fluessiggas123 = new Fluessiggas123();

        try {
            pfiffiggasWebcrawler.allePLZCrawlen();
            meinFluessiggasWebcrawler.allePLZCrawlen();
            fluessiggas123.allePLZCrawlen();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("Fehler! Crawler nicht erfolgreich");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
