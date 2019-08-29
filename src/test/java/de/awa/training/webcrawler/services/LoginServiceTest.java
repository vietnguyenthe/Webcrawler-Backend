package de.awa.training.webcrawler.services;


import de.awa.training.webcrawler.entity.PostleitzahlenEntity;
import de.awa.training.webcrawler.entity.UnternehmenEntity;
import de.awa.training.webcrawler.model.Login;
import de.awa.training.webcrawler.model.PreiseingabeUnternehmen;
import de.awa.training.webcrawler.repository.PLZRepository;
import de.awa.training.webcrawler.repository.PreiseingabeUnternehmenRepository;
import de.awa.training.webcrawler.repository.UnternehemensRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class LoginServiceTest {

    // Mockito falls zu Mockende Objekte vorhanden
    @Mock
    UnternehemensRepository unternehemensRepository;

    @Mock
    PreiseingabeUnternehmenRepository preiseingabeUnternehmenRepository;

    @Mock
    PLZRepository plzRepository;

    // InjectMocks
    @InjectMocks
    LoginService loginService;

    @InjectMocks
    PreiseingabeUnternehmen preiseingabeUnternehmen;


    // Testing der Methode "CheckLoginDaten"
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
        Assert.assertEquals(returnStatement,"erfolgreich");
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
        Assert.assertEquals(returnStatement,"fehlgeschlagen");
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
        Assert.assertEquals(returnStatement,"fehlgeschlagen");
    }




    // Testing der Methode "preiseingabeLogin"
    @Test
    public void erfolgreichePreiseingabe(){
        // Mockingvorbereiten
        MockitoAnnotations.initMocks(this);

        List<UnternehmenEntity> liste = new ArrayList<>();
        liste.add(createUnternehmenEntity("Kazim", "hallo"));
        liste.add(createUnternehmenEntity("Viet", "tag"));
        liste.add(createUnternehmenEntity("Felix","test"));
        Mockito.when(unternehemensRepository.findAll()).thenReturn(liste);

        List<PostleitzahlenEntity> plzListe = new ArrayList<>();
        plzListe.add(new PostleitzahlenEntity(1,"50212"));
        plzListe.add(new PostleitzahlenEntity(2,"50321"));
        Mockito.when(plzRepository.findAll()).thenReturn(plzListe);

        int i = 3556498;
        loginService.aktuellerNutzer = i;


/*        String aktuellerNutzer = "test";
        Mockito.when(aktuellerNutzer).thenReturn("test");*/


        // 1. Testfall vorbereiten
        PreiseingabeUnternehmen preiseingabeUnternehmen = new PreiseingabeUnternehmen();
/*        preiseingabeUnternehmen.setName("test");
        preiseingabeUnternehmen.setKennwort("test");*/
        preiseingabeUnternehmen.setPostleitzahl("50321");
        preiseingabeUnternehmen.setPreis2700Liter("27,00");
        preiseingabeUnternehmen.setPreis4850Liter("25,00");
        preiseingabeUnternehmen.setPreis6400Liter("23,00");

        // 2. Test durchführen
        String returnStatement = loginService.preiseingabeLogin(preiseingabeUnternehmen);

        //CheckloginDaten
        // 3. rgebnisse prüfen (Assert.assertEquals....)
        Assert.assertEquals(returnStatement,"erfolgreich");
    }



    @Test
    public void preiseingabenichterfolgreich(){
        // Mockingvorbereiten
        MockitoAnnotations.initMocks(this);

        List<UnternehmenEntity> liste = new ArrayList<>();
        liste.add(createUnternehmenEntity("Kazim", "hallo"));
        liste.add(createUnternehmenEntity("Viet", "tag"));
        liste.add(createUnternehmenEntity("Felix","test"));
        Mockito.when(unternehemensRepository.findAll()).thenReturn(liste);

        List<PostleitzahlenEntity> plzListe = new ArrayList<>();
        plzListe.add(new PostleitzahlenEntity(1,"50212"));
        plzListe.add(new PostleitzahlenEntity(2,"50321"));
        Mockito.when(plzRepository.findAll()).thenReturn(plzListe);

        int i = 3556498;
        loginService.aktuellerNutzer = i;


/*        String aktuellerNutzer = "test";
        Mockito.when(aktuellerNutzer).thenReturn("test");*/


        // 1. Testfall vorbereiten
        PreiseingabeUnternehmen preiseingabeUnternehmen = new PreiseingabeUnternehmen();
/*        preiseingabeUnternehmen.setName("test");
        preiseingabeUnternehmen.setKennwort("test");*/
        preiseingabeUnternehmen.setPostleitzahl("50321");
        preiseingabeUnternehmen.setPreis2700Liter("");
        preiseingabeUnternehmen.setPreis4850Liter("25,00");
        preiseingabeUnternehmen.setPreis6400Liter("23,00");

        // 2. Test durchführen
        String returnStatement = loginService.preiseingabeLogin(preiseingabeUnternehmen);

        //CheckloginDaten
        // 3. Ergebnisse prüfen (Assert.assertEquals....)
        Assert.assertEquals("leerer String",returnStatement, "leer");
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
