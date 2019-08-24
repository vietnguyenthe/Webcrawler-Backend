package de.awa.training.webcrawler.services;

import de.awa.training.webcrawler.entity.EntityInterface;
import de.awa.training.webcrawler.entity.PostleitzahlenEntity;
import de.awa.training.webcrawler.entity.UnternehmenEntity;
import de.awa.training.webcrawler.repository.PLZRepository;
import de.awa.training.webcrawler.repository.PfiffiggasRepository;
import de.awa.training.webcrawler.repository.UnternehemensRepository;
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

    @Autowired
    PLZRepository plzRepository;

    @Autowired
    UnternehemensRepository unternehemensRepository;

    private int UnternehmensID;

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
        //start(9, 0, 0, "51570");
        /*start(9, 25, 0, "52152");
        start(15, 26, 0, "53804");
        start(15, 27, 0, "54290");
        start(15, 28, 0, "55543");
        start(15, 29, 0, "56072");
        start(15, 30, 0, "57539");
        start(15, 31, 0, "58119");*/

    }

    public void preiseInDatenbankschreiben(JpaRepository repository,
                                           EntityInterface entityInterface,
                                           String preis2700,
                                           String preis4850,
                                           String preis6400,
                                           String plz){

        Date date = new Date(new java.util.Date().getTime());
        Integer plzID = suchePLZ(plz);
        entityInterface.setPreis2700Liter(preis2700);
        entityInterface.setPreis4850Liter(preis4850);
        entityInterface.setPreis6400Liter(preis6400);
        entityInterface.setPostleitzahlenId(plzID);
        entityInterface.setUnternehmenId(getUnternehmensID());
        entityInterface.setDatum(date);
        repository.save(entityInterface);
    }

    public Integer suchePLZ(String plz){
        List<PostleitzahlenEntity> liste = plzRepository.findAll();
        Integer plzID = null;
        for (PostleitzahlenEntity element: liste) {
            if(element.getPlz().equals(plz)){
                plzID = element.getId();
            }
        }
        return plzID;
    }


    public static void waitForAction(final double time) {
        try {
            Thread.sleep((int) (time * 1000));
        } catch (final InterruptedException e) {
            e.printStackTrace();

        }
    }

    public abstract int getUnternehmensID();
}
