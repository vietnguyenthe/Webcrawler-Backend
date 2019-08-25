package de.awa.training.webcrawler.webcrawlerbackend.demo.ServiceTest;

import de.awa.training.webcrawler.entity.Fluessiggas123Entity;
import de.awa.training.webcrawler.entity.MeinfluessiggasEntity;
import de.awa.training.webcrawler.entity.PfiffiggasEntity;
import de.awa.training.webcrawler.entity.PostleitzahlenEntity;
import de.awa.training.webcrawler.model.PreisDaten;
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
    ArrayList<MeinfluessiggasEntity> meinFluessiggasgemockteListe = new ArrayList<>();
    ArrayList<Fluessiggas123Entity> fluessiggas123gemockteListe = new ArrayList<>();
    List<PostleitzahlenEntity> plzliste = new ArrayList<>();

    @Before
    public void testDatenVorbereiten(){
        pfiffiggemockteListe.add(new PfiffiggasEntity(1,"29","30","31",1,1));
        pfiffiggemockteListe.add(new PfiffiggasEntity(2,"32","33","34",1,1));
        pfiffiggemockteListe.add(new PfiffiggasEntity(3,"35","36","37",1,1));

        meinFluessiggasgemockteListe.add(new MeinfluessiggasEntity(1,"39","40","41",1,2));
        meinFluessiggasgemockteListe.add(new MeinfluessiggasEntity(2,"42","43","44",1,2));
        meinFluessiggasgemockteListe.add(new MeinfluessiggasEntity(3,"45","46","47",1,2));

        fluessiggas123gemockteListe.add(new Fluessiggas123Entity(1,"49","50","51",1,3));
        fluessiggas123gemockteListe.add(new Fluessiggas123Entity(2,"52","53","54",1,3));
        fluessiggas123gemockteListe.add(new Fluessiggas123Entity(3,"55","56","57",1,3));

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


    @Test
    public void sammlePreiseTEST(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(pfiffiggasRepository.findAll()).thenReturn(pfiffiggemockteListe);
        Mockito.when(meinFluessiggasRepository.findAll()).thenReturn(meinFluessiggasgemockteListe);
        Mockito.when(fluessiggas123Repository.findAll()).thenReturn(fluessiggas123gemockteListe);

        List<PreisDaten> erwarteteListe = anfragenService.sammlePreise(1,1);

        Assert.assertEquals(erwarteteListe.size(),3);
        Assert.assertEquals(erwarteteListe.get(0).toString(),new PreisDaten(3,"56").toString());
        Assert.assertEquals(erwarteteListe.get(1).toString(),new PreisDaten(2,"46").toString());
        Assert.assertEquals(erwarteteListe.get(2).toString(),new PreisDaten(1,"36").toString());
    }


}
