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
public class PfiffiggasWebcrawler extends Crawler{


    public void tankcrawlen(String plz,WebDriver driver, ChromeOptions chromeOptions) {
        driver.get("https://pfiffiggas.de/#!/mein-preis");

        Select element = new Select(driver.findElement(By.tagName("Select")));
        element.selectByVisibleText("1.2t (2700 Liter)");

        WebElement text = driver.findElement(By.cssSelector("input[class='form-control ng-pristine ng-untouched ng-valid ng-isolate-scope ng-empty ng-valid-maxlength']"));
        text.sendKeys(plz);
        waitForAction(3.0);

        text = driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div[1]/span"));
        System.out.println("PfiffiggasPreis "+ text.getText());
        preise.add(text.getText());
        waitForAction(2.0);

        element.selectByVisibleText("2.1t (4850 Liter)");
        waitForAction(3.0);
        text = driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div[1]/span"));
        System.out.println("PfiffiggasPreis "+ text.getText());
        preise.add(text.getText());
        waitForAction(2.0);
        element.selectByVisibleText("2.9t (6400 Liter)");
        waitForAction(3.0);
        text = driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div/div[2]/div/div/div[2]/div/div[1]/div/div[1]/span"));
        System.out.println("PfiffiggasPreis "+ text.getText());
        preise.add(text.getText());
        waitForAction(2.0);
        driver.close();
   }

}

