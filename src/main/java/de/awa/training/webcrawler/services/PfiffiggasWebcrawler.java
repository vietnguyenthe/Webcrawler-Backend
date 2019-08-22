package de.awa.training.webcrawler.services;

import de.awa.training.webcrawler.repository.PfiffiggasRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PfiffiggasWebcrawler extends Crawler{

    @Autowired
    PfiffiggasRepository pfiffiggasRepository;


    public void tankcrawlen(String plz,WebDriver driver, ChromeOptions chromeOptions) throws NoSuchElementException {
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

