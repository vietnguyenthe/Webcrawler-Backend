package webcrawlerbackend.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;

import static services.PfiffiggasWebcrawler.start;
import static services.Test.laufen;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        try {
            start(17,3,0,"53804");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Fehler! Crawler nicht erfolgreich");
        }
    }

}
