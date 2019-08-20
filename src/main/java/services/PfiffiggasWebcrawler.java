package services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.text.ParseException;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

@Service
public class PfiffiggasWebcrawler extends Crawler {



    public static List<String> preise = new ArrayList<>();
    public static List<String>plz = new ArrayList<>();


    public static void start(int std, int min, int sek, String plz) throws ParseException {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, std);
        today.set(Calendar.MINUTE, min);
        today.set(Calendar.SECOND, sek);
        Timer timer = new Timer();
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                tankcrawlen(plz,driver,chromeOptions);
            }
        };
        timer.scheduleAtFixedRate(timerTask, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }

    public static void tankcrawlen(String plz,WebDriver driver, ChromeOptions chromeOptions) {
        driver.get("https://pfiffiggas.de/#!/mein-preis");
        Select element = new Select(driver.findElement(By.tagName("Select")));
        element.selectByVisibleText("1.2t (2700 Liter)");
        WebElement text = driver.findElement(By.cssSelector("input[class='form-control ng-pristine ng-untouched ng-valid ng-isolate-scope ng-empty ng-valid-maxlength']"));
        text.sendKeys(plz);
        waitForAction(3.0);
        text = driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div[1]/span"));
        System.out.println(text.getText());
        preise.add(text.getText());
        waitForAction(2.0);
        element.selectByVisibleText("2.1t (4850 Liter)");
        waitForAction(3.0);
        text = driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div[1]/span"));
        System.out.println(text.getText());
        preise.add(text.getText());
        waitForAction(2.0);
        element.selectByVisibleText("2.9t (6400 Liter)");
        waitForAction(3.0);
        text = driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div[1]/span"));
        System.out.println(text.getText());
        preise.add(text.getText());
        waitForAction(2.0);
        driver.close();
   }




   public static void allePLZCrawlen() throws ParseException {
        start(11,34,0, "51570");
        start(11,35,0, "52152");
        start(11,36,0, "53804");
        start(11,37,0, "54290");
        start(11,38,0, "55543");
        start(11,39,0, "56072");
        start(11,40,0, "57539");
        start(11,41,0, "58119");
        System.out.println("Crawler beendet");

   }

    public static void plzGenerieren(){

    }


    public static void waitForAction(final double time) {
        try {
            Thread.sleep((int) (time * 1000));
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }
}

