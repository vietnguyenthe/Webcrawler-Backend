package webcrawlerbackend.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;

import static services.PfiffiggasWebcrawler.allePLZCrawlen;
import static services.PfiffiggasWebcrawler.start;
import static services.Test.laufen;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        try {
            allePLZCrawlen();
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Fehler! Crawler nicht erfolgreich");
        }
    }

}
