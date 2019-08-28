package de.awa.training.webcrawler.webcrawlerbackend.demo.servicesTest;

import de.awa.training.webcrawler.entity.UnternehmenEntity;
import de.awa.training.webcrawler.model.Login;
import de.awa.training.webcrawler.repository.UnternehemensRepository;
import de.awa.training.webcrawler.services.LoginService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class LoginServiceTest {

    // Mockito falls zu Mockende Objekte vorhanden
    @Mock
    UnternehemensRepository unternehemensRepository;

    // InjectMocks
    @InjectMocks
    LoginService loginService;

    @Test
    public void checkRichtigeLoginDaten(){
        // Mockingvorbereiten
        MockitoAnnotations.initMocks(this);

        List<UnternehmenEntity> unternehmensDatenListen = new ArrayList<>();
        unternehmensDatenListen.add(createUnternehmenEntity("Kazim", "hallo"));
        unternehmensDatenListen.add(createUnternehmenEntity("Viet", "tag"));

        Mockito.when(unternehemensRepository.findAll()).thenReturn(unternehmensDatenListen);

        // 1. Testfall vorbereiten
        Login login = new Login();
        login.setLoginName("Kazim");
        login.setLoginPasswort("hallo");

        // 2. Test durchführen
        //CheckloginDaten
        String returnStatement = loginService.checkLoginDaten(login);

        // 3. rgebnisse prüfen (Assert.assertEquals....)
        Assert.assertTrue("erfolgreich",true);
    }

    @Test
    public void checkFalscheBenutzernamenLoginDaten(){
        // Mockingvorbereiten
        MockitoAnnotations.initMocks(this);

        List<UnternehmenEntity> unternehmensDatenListen = new ArrayList<>();
        unternehmensDatenListen.add(createUnternehmenEntity("Kazim", "hallo"));
        unternehmensDatenListen.add(createUnternehmenEntity("Viet", "tag"));
        unternehmensDatenListen.add(createUnternehmenEntity("Felix","moin"));

        Mockito.when(unternehemensRepository.findAll()).thenReturn(unternehmensDatenListen);

        // 1. Testfall vorbereiten
        Login login = new Login();
        login.setLoginName("Oli");
        login.setLoginPasswort("hallo");

        // 2. Test durchführen
        //CheckloginDaten
        String returnStatement = loginService.checkLoginDaten(login);

        // 3. rgebnisse prüfen (Assert.assertEquals....)
        Assert.assertTrue("fehlgeschlagen",true);
    }


    @Test
    public void checkFalschesKennwortLoginDaten(){
        // Mockingvorbereiten
        MockitoAnnotations.initMocks(this);

        List<UnternehmenEntity> unternehmensDatenListen = new ArrayList<>();
        unternehmensDatenListen.add(createUnternehmenEntity("Kazim", "hallo"));
        unternehmensDatenListen.add(createUnternehmenEntity("Viet", "tag"));
        unternehmensDatenListen.add(createUnternehmenEntity("Felix","moin"));

        Mockito.when(unternehemensRepository.findAll()).thenReturn(unternehmensDatenListen);

        // 1. Testfall vorbereiten
        Login login = new Login();
        login.setLoginName("Viet");
        login.setLoginPasswort("moin");

        // 2. Test durchführen
        //CheckloginDaten
        String returnStatement = loginService.checkLoginDaten(login);

        // 3. rgebnisse prüfen (Assert.assertEquals....)
        Assert.assertTrue("fehlgeschlagen",true);
    }




    public UnternehmenEntity createUnternehmenEntity(String benutzername, String kennwort){
        UnternehmenEntity unternehmen = new UnternehmenEntity();
        unternehmen.setBenutzername(benutzername);
        String passwort = kennwort;
        int hashedpasswort = passwort.hashCode();
        unternehmen.setPasswort(hashedpasswort);
        unternehmen.setAdresse("a");
        unternehmen.setId(1);
        unternehmen.setName("abc");
        unternehmen.setOrt("Köln");
        unternehmen.setPlz("50");
        return unternehmen;
    }


}
