package services;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class MeinFluessiggasWebcrawler {


    public static void laufen() throws ParseException {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 8);
        today.set(Calendar.MINUTE, 26);
        today.set(Calendar.SECOND, 0);
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.setProperty("webdriver.chrome.driver",
                        "C:\\Users\\Viet\\Desktop\\ProjekteOrdner\\Webcrawler\\webcrawler-backend\\src\\main\\resources\\chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--startup-maximized");// --headless   --startup-maximized
                WebDriver driver = new ChromeDriver(chromeOptions);
                driver.get("https://www.meinfluessiggas.de/");
                JavascriptExecutor jse = (JavascriptExecutor) driver;

                ////*[@id="product-options-wrapper"]/div[5]/div[1]/div[1]
                WebElement element = driver.findElement(By.xpath("//*[@id=\"product-options-wrapper\"]/div[5]/div[1]/div[1]"));
                element.click();
                jse.executeScript("window.scrollBy(0,200)");
                waitForAction(2.0);
                WebElement input = driver.findElement(By.xpath("//*[@id=\"select_6677_chosen\"]/div/div/input"));
                input.sendKeys("50321");
                jse.executeScript("window.scrollBy(0,200)");
                waitForAction(1.0);
                driver.findElement(By.id("options_2_text")).sendKeys("0");
                System.out.println(driver.findElement(By.id("priceNetto")).getText());



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
}
