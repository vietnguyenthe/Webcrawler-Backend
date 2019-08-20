package services;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;


@Service
public class Fluessiggas123Webcrawler {

    // Voraussetzungen setzen
    String url = "https://www.123-fluessiggas.de/";
    String plz = "50321";
    String fuellstand = "30";

    String behaelterKleinId = "behaelter1";
    String behaelterMittelId = "behaelter2";
    String behaelterGroßId = "behaelter3";
    String plzEingabeId = "behaelterstandort";
    String fuellstandEingabeId = "fuellstand";
    String bestellmengeId = "vollfuellung";
    String ausgabeBestellmengeId ="order_quantity";
    String ausgabePreisProLiterId ="price_liter_net";

    String bestellmenge = "";
    String preisProLiterNetto = "";

    // Starte Chrome und Startseite
//    WebDriver driver = new ChromeDriver();

    // JavascriptExector hilft beim Runterscrollen der Seite, weil ansonsten Silenium die unteren Elemente nicht anklicken kann
    JavascriptExecutor executor = (JavascriptExecutor)this.starteDriver();

    public WebDriver starteDriver (){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    WebDriver driver = this.starteDriver();



/*    public Fluessiggas123Webcrawler(WebDriver driver) {
        this.url = url;
        this.driver = driver;
        this.executor = executor;

        // Starte Chrome und Startseite
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");

        driver.get(url);
        // JavascriptExector hilft beim Runterscrollen der Seite, weil ansonsten Silenium die unteren Elemente nicht anklicken kann
        executor = (JavascriptExecutor)driver;
    }*/

    public void start(){
        // Starte Chrome und Startseite
//        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");
  /*      ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        */

        driver.get(url);
        // JavascriptExector hilft beim Runterscrollen der Seite, weil ansonsten Silenium die unteren Elemente nicht anklicken kann
//        JavascriptExecutor executor = (JavascriptExecutor)driver;
//        executor = (JavascriptExecutor)driver;

        Fluessiggas123Webcrawler fluessiggas123Webcrawler = new Fluessiggas123Webcrawler( );
        fluessiggas123Webcrawler.behaelterGroesseAuswaehlen(behaelterKleinId);
        fluessiggas123Webcrawler.plzAuswaehlen();
        fluessiggas123Webcrawler.fuellstandBestellmengeAuswaehlen();
        fluessiggas123Webcrawler.liefermengenAusgabe();
        fluessiggas123Webcrawler.preisAusgabe();
    }


    public void behaelterGroesseAuswaehlen (String behaelterGroessenId){
        // Finde das Element für den Kleinenbehaelter und wähle es aus
        WebElement behaelter = driver.findElement(By.id(behaelterGroessenId));
        executor.executeScript("arguments[0].click();", behaelter);
    }

    public void plzAuswaehlen(){
        // Finde Input Feld für Postleitzahl und füge die PLZ ein
        WebElement plzEingabe = driver.findElement(By.id(plzEingabeId));
        executor.executeScript("arguments[0].click()", plzEingabe);
        plzEingabe.sendKeys(plz);
    }

    public void fuellstandBestellmengeAuswaehlen (){
        // FInde Input Feld für den Füllstand und schreibe immer 30 rein egal für welche Behältergröße
        WebElement fuellstandEingabe = driver.findElement(By.id(fuellstandEingabeId));
        executor.executeScript("arguments[0].click()", fuellstandEingabe);
        fuellstandEingabe.sendKeys(fuellstand);

        // Finde das Element für die Bestellmenge und wähle immer Vollieferung aus egal für welche Behältergröße
        WebElement bestellmengeEingabe = driver.findElement(By.id(bestellmengeId));
        executor.executeScript("arguments[0].click();", bestellmengeEingabe);
    }

    public void liefermengenAusgabe(){
        // Folgende Schritte ziehen die wichtigen Daten aus der Seite
        // 1. Bestellmenge
        WebElement ausgabeBestellmenge = driver.findElement(By.id(ausgabeBestellmengeId));
        bestellmenge = ausgabeBestellmenge.getText();
        System.out.println(bestellmenge);
    }

    public void preisAusgabe (){
        // 2. Preis pro Liter in netto
        WebElement ausgabePreisProLiter = driver.findElement(By.id(ausgabePreisProLiterId));
        preisProLiterNetto = ausgabePreisProLiter.getText();
        System.out.println(preisProLiterNetto);
    }


/*
    public static void run (){
        // Voraussetzungen setzen
        String url = "https://www.123-fluessiggas.de/";
        String plz = "50321";
        String fuellstand = "30";

        String behaelterKleinId = "behaelter1";
        String behaelterMittelId = "behaelter2";
        String behaelterGroßId = "behaelter3";
        String plzEingabeId = "behaelterstandort";
        String fuellstandEingabeId = "fuellstand";
        String bestellmengeId = "vollfuellung";
        String ausgabeBestellmengeId ="order_quantity";
        String ausgabePreisProLiterId ="price_liter_net";

        String bestellmenge = "";
        String preisProLiterNetto = "";

        // Starte Chrome und Startseite
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kcoep\\eclipse-workspace\\meinProjekt1\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        // JavascriptExector hilft beim Runterscrollen der Seite, weil ansonsten Silenium die unteren Elemente nicht anklicken kann
        JavascriptExecutor executor = (JavascriptExecutor)driver;

        // Finde das Element für den Kleinenbehaelter und wähle es aus
        WebElement behaelter = driver.findElement(By.id(behaelterKleinId));
        executor.executeScript("arguments[0].click();", behaelter);

        // Finde Input Feld für Postleitzahl und füge die PLZ ein
        WebElement plzEingabe = driver.findElement(By.id(plzEingabeId));
        executor.executeScript("arguments[0].click()", plzEingabe);
        plzEingabe.sendKeys(plz);

        // FInde Input Feld für den Füllstand und schreibe immer 30 rein egal für welche Behältergröße
        WebElement fuellstandEingabe = driver.findElement(By.id(fuellstandEingabeId));
        executor.executeScript("arguments[0].click()", fuellstandEingabe);
        fuellstandEingabe.sendKeys(fuellstand);

        // Finde das Element für die Bestellmenge und wähle immer Vollieferung aus egal für welche Behältergröße
        WebElement bestellmengeEingabe = driver.findElement(By.id(bestellmengeId));
        executor.executeScript("arguments[0].click();", bestellmengeEingabe);

        // Folgende Schritte ziehen die wichtigen Daten aus der Seite
        // 1. Bestellmenge
        WebElement ausgabeBestellmenge = driver.findElement(By.id(ausgabeBestellmengeId));
        bestellmenge = ausgabeBestellmenge.getText();
        System.out.println(bestellmenge);

        // 2. Preis pro Liter in netto
        WebElement ausgabePreisProLiter = driver.findElement(By.id(ausgabePreisProLiterId));
        preisProLiterNetto = ausgabePreisProLiter.getText();
        System.out.println(preisProLiterNetto);
    }
*/


    public static void waitForAction(final double time) {
        try {
            Thread.sleep((int) (time * 1000));
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }












}
