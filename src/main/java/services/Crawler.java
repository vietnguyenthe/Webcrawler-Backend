package services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public abstract class Crawler{


        List<String> preise = new ArrayList<>();
        List<String> plz = new ArrayList<>();


        public static void start ( int std, int min, int sek, String plz) throws ParseException {
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


        public static void tankcrawlen (String plz, WebDriver driver, ChromeOptions chromeOptions){}


        public static void allePLZCrawlen () throws ParseException{
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


        public static void plzGenerieren (){}


        public static void waitForAction ( final double time){
            try {
                Thread.sleep((int) (time * 1000));
             } catch (final InterruptedException e) {
                e.printStackTrace();
            }
    }


































}
