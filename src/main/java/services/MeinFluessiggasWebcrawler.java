package services;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
public class MeinFluessiggasWebcrawler extends Crawler{


    @Override
    public void tankcrawlen(String plz, WebDriver driver, ChromeOptions chromeOptions) {
        driver.get("https://www.meinfluessiggas.de/");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,300)");

        //1,2-Tonnen-Container
        waitForAction(2.0);
        driver.findElement(By.xpath("//*[@id=\"product-options-wrapper\"]/div[5]/div[1]/div[2]")).click();
        waitForAction(2.0);
        driver.findElement(By.xpath("//*[@id=\"select_6678_chosen\"]/div/div/input")).sendKeys(plz);
        waitForAction(2.0);
        driver.findElement(By.id("options_2_text")).sendKeys("0");
        System.out.println("MeinFlüssiggas " + driver.findElement(By.id("priceNetto")).getText());

        //2,1-Tonnen-Container
        waitForAction(3.0);
        driver.findElement(By.xpath("//*[@id=\"product-options-wrapper\"]/div[5]/div[1]/div[3]")).click();
        waitForAction(2.0);
        driver.findElement(By.xpath("//*[@id=\"select_6679_chosen\"]/div/div/input")).sendKeys(plz);
        waitForAction(2.0);
        driver.findElement(By.id("options_2_text")).sendKeys("0");
        System.out.println("MeinFlüssiggas " + driver.findElement(By.id("priceNetto")).getText());

        //2,9-Tonnen-Container
        waitForAction(3.0);
        driver.findElement(By.xpath("//*[@id=\"product-options-wrapper\"]/div[5]/div[1]/div[4]")).click();
        waitForAction(2.0);
        driver.findElement(By.xpath("//*[@id=\"select_6680_chosen\"]/div/div/input")).sendKeys("50321");
        waitForAction(2.0);
        driver.findElement(By.id("options_2_text")).sendKeys("0");
        System.out.println("MeinFlüssiggas " + driver.findElement(By.id("priceNetto")).getText());
        driver.close();

    }

}

// System.setProperty("webdriver.chrome.driver",
//System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
//ChromeOptions chromeOptions = new ChromeOptions();
// chromeOptions.addArguments("--startup-maximized");// --headless   --startup-maximized
// WebDriver driver = new ChromeDriver(chromeOptions);

/*0,8-Tonnen-Container
driver.findElement(By.xpath("//*[@id=\"product-options-wrapper\"]/div[5]/div[1]/div[1]")).click();
waitForAction(3.0);
driver.findElement(By.xpath("//*[@id=\"select_6677_chosen\"]/div/div/input")).sendKeys(plz);
waitForAction(2.0);
driver.findElement(By.id("options_2_text")).sendKeys("0");
System.out.println("MeinFlüssiggas " + driver.findElement(By.id("priceNetto")).getText());*/

