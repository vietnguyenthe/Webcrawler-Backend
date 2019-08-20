package webcrawlerbackend.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import services.Fluessiggas123;
import services.PfiffiggasWebcrawler;

import java.text.ParseException;


@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {


        SpringApplication.run(DemoApplication.class, args);

        PfiffiggasWebcrawler pfiffiggasWebcrawler = new PfiffiggasWebcrawler();
        Fluessiggas123 fluessiggas123 = new Fluessiggas123();

        try {
            pfiffiggasWebcrawler.allePLZCrawlen();
            fluessiggas123.allePLZCrawlen();

        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Fehler! Crawler nicht erfolgreich");
        }
    }

}
