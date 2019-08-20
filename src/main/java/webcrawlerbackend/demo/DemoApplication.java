package webcrawlerbackend.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.context.ConfigurableReactiveWebApplicationContext;
import services.Crawler;
import services.Fluessiggas123;
import services.MeinFluessiggasWebcrawler;
import services.PfiffiggasWebcrawler;

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
            Crawler.waitForAction(15);
            meinFluessiggasWebcrawler.allePLZCrawlen();
            Crawler.waitForAction(15);
            fluessiggas123.allePLZCrawlen();

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("Fehler! Crawler nicht erfolgreich");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
