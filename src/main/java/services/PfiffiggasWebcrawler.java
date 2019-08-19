package services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Service
public class PfiffiggasWebcrawler {

    public static void start(int std, int min, int sek, String plz) throws ParseException {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, std);
        today.set(Calendar.MINUTE, min);
        today.set(Calendar.SECOND, sek);
        Timer timer = new Timer();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Felix Weyer\\Desktop\\webCrawler\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriver driver = new ChromeDriver(chromeOptions);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mittlererTank(plz, chromeOptions, driver);
            }
        };
        timer.scheduleAtFixedRate(timerTask, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }

    public static void waitForAction(final double time) {
        try {
            Thread.sleep((int) (time * 1000));
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void mittlererTank(String plz, ChromeOptions chromeOptions, WebDriver driver) {
        chromeOptions.addArguments("--startup-maximized");// --headless   --startup-maximized
        driver.get("https://pfiffiggas.de/#!/mein-preis");
        Select element = new Select(driver.findElement(By.tagName("Select")));
        element.selectByVisibleText("2.1t (4850 Liter)");
        WebElement text = driver.findElement(By.cssSelector("input[class='form-control ng-pristine ng-untouched ng-valid ng-isolate-scope ng-empty ng-valid-maxlength']"));
        text.sendKeys("53819");
        waitForAction(3.0);
        text = driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div[1]/span"));
        String preis = text.getText();
        System.out.println(preis);
        waitForAction(2.0);
        driver.close();
    }

}

