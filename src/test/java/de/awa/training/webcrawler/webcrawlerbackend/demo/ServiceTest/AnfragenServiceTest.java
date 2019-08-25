package de.awa.training.webcrawler.webcrawlerbackend.demo.ServiceTest;

import de.awa.training.webcrawler.entity.PfiffiggasEntity;
import de.awa.training.webcrawler.entity.PostleitzahlenEntity;
import de.awa.training.webcrawler.repository.*;
import de.awa.training.webcrawler.services.AnfragenService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.List;

public class AnfragenServiceTest {

    @Mock
    PfiffiggasRepository pfiffiggasRepository;

    @Mock
    MeinFluessiggasRepository meinFluessiggasRepository;

    @Mock
    Fluessiggas123Repository fluessiggas123Repository;

    @Mock
    PLZRepository plzRepository;

    @Mock
    UnternehemensRepository unternehemensRepository;

    @InjectMocks
    AnfragenService anfragenService;

    ArrayList<PfiffiggasEntity> pfiffiggemockteListe = new ArrayList<>();
    List<PostleitzahlenEntity> plzliste = new ArrayList<>();

    @Before
    public void testDatenVorbereiten(){
        pfiffiggemockteListe.add(new PfiffiggasEntity(1,"29","30","31",1,1));
        pfiffiggemockteListe.add(new PfiffiggasEntity(2,"32","33","34",1,1));
        pfiffiggemockteListe.add(new PfiffiggasEntity(3,"35","36","37",1,1));
        plzliste.add(new PostleitzahlenEntity(1,"51570"));
        plzliste.add(new PostleitzahlenEntity(2,"52152"));
        plzliste.add(new PostleitzahlenEntity(3,"53804"));

    }

    @Test
    public void holePLZidTEST(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(plzRepository.findAll()).thenReturn(plzliste);
        Integer id = anfragenService.holePLZid("51570");
        Integer id2 = anfragenService.holePLZid("52152");
        Integer id3 = anfragenService.holePLZid("55555");
        Assert.assertEquals(Integer.valueOf(1),id);
        Assert.assertEquals(Integer.valueOf(2),id2);
        Assert.assertNull(null,id3);
    }

    @Test
    public void tankgrößeInIndexumwandelnTEST(){
        MockitoAnnotations.initMocks(this);
        Integer kleinerTank=anfragenService.tankgrößeInIndexumwandeln("preis2700liter");
        Integer mittlererTank=anfragenService.tankgrößeInIndexumwandeln("preis4850liter");
        Integer tankIDnichtvorhanden=anfragenService.tankgrößeInIndexumwandeln("test");
        Assert.assertEquals(Integer.valueOf(0),kleinerTank);
        Assert.assertEquals(Integer.valueOf(1),mittlererTank);
        Assert.assertNull(tankIDnichtvorhanden);
    }

    @Test
    public void holePreisausEntitytabelleTEST(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(pfiffiggasRepository.findAll()).thenReturn(pfiffiggemockteListe);
        String ergebnis = anfragenService.holePreisausEntitytabelle(pfiffiggasRepository,1,0);
        String ergebnis2 = anfragenService.holePreisausEntitytabelle(pfiffiggasRepository,1,1);
        String ergebnis3 = anfragenService.holePreisausEntitytabelle(pfiffiggasRepository,2,3);
        Assert.assertEquals(ergebnis,"35");
        Assert.assertEquals(ergebnis2,"36");
        Assert.assertEquals(ergebnis3,"Kein Wert für die PLZ gefunden");
    }





}
