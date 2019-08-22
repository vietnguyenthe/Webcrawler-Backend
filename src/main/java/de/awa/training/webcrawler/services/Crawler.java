package de.awa.training.webcrawler.services;

import de.awa.training.webcrawler.entity.EntityInterface;
import de.awa.training.webcrawler.repository.PfiffiggasRepository;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;


public abstract class Crawler{


    public void start(int std, int min, int sek, String plz) throws ParseException, NoSuchElementException {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, std);
        today.set(Calendar.MINUTE, min);
        today.set(Calendar.SECOND, sek);
        Timer timer = new Timer();
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() throws NoSuchElementException {
                tankcrawlen(plz, driver, chromeOptions);
            }
        };
        timer.scheduleAtFixedRate(timerTask, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }


    public abstract void tankcrawlen(String plz, WebDriver driver, ChromeOptions chromeOptions) throws NoSuchElementException;

    public void allePLZCrawlen() throws ParseException,NoSuchElementException {
        start(15, 1, 0, "51570");
        start(15, 2, 0, "52152");
        start(15, 3, 0, "53804");
        start(15, 4, 0, "54290");
        start(15, 5, 0, "55543");
        start(15, 6, 0, "56072");
        start(15, 7, 0, "57539");
        start(15, 8, 0, "58119");

    }

    public void preiseInDatenbankschreiben(JpaRepository repository, EntityInterface entityInterface, String preis2700, String preis4850, String preis6400){
        Date date = new Date(12);
        entityInterface.setPreis2700Liter(preis2700);
        entityInterface.setPreis4850Liter(preis4850);
        entityInterface.setPreis6400Liter(preis6400);
        entityInterface.setDatum(date);
        repository.save(entityInterface);
    }


    public static void waitForAction(final double time) {
        try {
            Thread.sleep((int) (time * 1000));
        } catch (final InterruptedException e) {
            e.printStackTrace();

        }
    }
}
