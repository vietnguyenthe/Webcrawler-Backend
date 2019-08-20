package webcrawlerbackend.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import services.Fluessiggas123Webcrawler;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);


        Fluessiggas123Webcrawler fluessiggas123Webcrawler = new Fluessiggas123Webcrawler();
        fluessiggas123Webcrawler.start();



    }


}
