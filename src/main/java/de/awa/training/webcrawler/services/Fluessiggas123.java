package de.awa.training.webcrawler.services;

import de.awa.training.webcrawler.entity.PreiseingabeUnternehmenEntity;
import de.awa.training.webcrawler.repository.PreiseingabeUnternehmenRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.openqa.selenium.NoSuchElementException;


@Service
public class Fluessiggas123 extends Crawler {



    @Autowired
    PreiseingabeUnternehmenRepository preiseingabeUnternehmenRepository;

    private final int UnternehmensID = 3;

    public int getUnternehmensID() {
        return UnternehmensID;
    }

    @Override
    public void tankcrawlen(String plz, WebDriver driver, ChromeOptions chromeOptions) {

        try {
            String preis2700;
            String preis4850;
            String preis6400;

            driver.get("https://www.123-fluessiggas.de/");
            // JavascriptExector hilft beim Runterscrollen der Seite, weil ansonsten Silenium die unteren Elemente nicht anklicken kann
            JavascriptExecutor executor = (JavascriptExecutor)driver;

            // Finde das Element für den Kleinenbehaelter und wähle es aus
            WebElement behaelter = driver.findElement(By.id("behaelter1"));
            executor.executeScript("arguments[0].click();", behaelter);

            // Finde Input Feld für Postleitzahl und füge die PLZ ein
            WebElement plzEingabe = driver.findElement(By.id("behaelterstandort"));
            executor.executeScript("arguments[0].click()", plzEingabe);
            plzEingabe.sendKeys(plz);

            // FInde Input Feld für den Füllstand und schreibe immer 30 rein egal für welche Behältergröße
            WebElement fuellstandEingabe = driver.findElement(By.id("fuellstand"));
            executor.executeScript("arguments[0].click()", fuellstandEingabe);
            fuellstandEingabe.sendKeys("30");

            // Finde das Element für die Bestellmenge und wähle immer Vollieferung aus egal für welche Behältergröße
            WebElement bestellmengeEingabe = driver.findElement(By.id("vollfuellung"));
            executor.executeScript("arguments[0].click();", bestellmengeEingabe);

            // Folgende Schritte ziehen die wichtigen Daten aus der Seite
            // 1. Bestellmenge
            WebElement ausgabeBestellmenge = driver.findElement(By.id("order_quantity"));
            //preise.add(ausgabeBestellmenge.getText());
            //System.out.println("Flüssiggas123 Bestellmenge: " + ausgabeBestellmenge.getText());

            // 2. Preis pro Liter in netto
            WebElement ausgabePreisProLiter = driver.findElement(By.id("price_liter_net"));
            //preise.add(ausgabePreisProLiter.getText());
            System.out.println("Flüssiggas123 Preis pro Liter" + ausgabePreisProLiter.getText());
            preis2700 = ausgabePreisProLiter.getText();


            // FÜR MITTLEREN BEHÄLTER
            // Finde das Element für den MittlerenBehaelter und wähle es aus
            WebElement behaelterM = driver.findElement(By.id("behaelter2"));
            executor.executeScript("arguments[0].click();", behaelterM);
            // Finde das Element für die Bestellmenge und wähle immer Vollieferung aus egal für welche Behältergröße
            WebElement bestellmengeEingabeM = driver.findElement(By.id("vollfuellung"));
            executor.executeScript("arguments[0].click();", bestellmengeEingabeM);

            // Folgende Schritte ziehen die wichtigen Daten aus der Seite
            // 1. Bestellmenge
            WebElement ausgabeBestellmengeM = driver.findElement(By.id("order_quantity"));
            //preise.add(ausgabeBestellmengeM.getText());
            //System.out.println("Flüssiggas123 Bestellmenge: " + ausgabeBestellmengeM.getText());

            // 2. Preis pro Liter in netto
            WebElement ausgabePreisProLiterM = driver.findElement(By.id("price_liter_net"));
            //preise.add(ausgabePreisProLiterM.getText());
            System.out.println("Flüssiggas123 Preis pro Liter" + ausgabePreisProLiterM.getText());
            preis4850 = ausgabePreisProLiterM.getText();




            // FÜR GROSSEN BEHÄLTER
            // Finde das Element für den MittlerenBehaelter und wähle es aus
            WebElement behaelterG = driver.findElement(By.id("behaelter3"));
            executor.executeScript("arguments[0].click();", behaelterG);
            // Finde das Element für die Bestellmenge und wähle immer Vollieferung aus egal für welche Behältergröße
            WebElement bestellmengeEingabeG = driver.findElement(By.id("vollfuellung"));
            executor.executeScript("arguments[0].click();", bestellmengeEingabeG);

            // Folgende Schritte ziehen die wichtigen Daten aus der Seite
            // 1. Bestellmenge
            WebElement ausgabeBestellmengeG = driver.findElement(By.id("order_quantity"));
            //preise.add(ausgabeBestellmengeG.getText());
            //System.out.println("Flüssiggas123 Bestellmenge: " + ausgabeBestellmengeG.getText());

            // 2. Preis pro Liter in netto
            WebElement ausgabePreisProLiterG = driver.findElement(By.id("price_liter_net"));
            //preise.add(ausgabePreisProLiterG.getText());
            System.out.println("Flüssiggas123 Preis pro Liter" + ausgabePreisProLiterG.getText());
            preis6400 = ausgabePreisProLiterG.getText();
            driver.close();

            PreiseingabeUnternehmenEntity preiseingabeUnternehmenEntity = new PreiseingabeUnternehmenEntity();
            preiseInDatenbankschreiben(preiseingabeUnternehmenRepository,preiseingabeUnternehmenEntity,preis2700,preis4850,preis6400,plz);

        }catch (NoSuchElementException e){
            System.out.println("Crawler bei Flüssiggas123 nicht erfolgreich für " + plz);
        }finally {
            driver.quit();
        }


    }
}
