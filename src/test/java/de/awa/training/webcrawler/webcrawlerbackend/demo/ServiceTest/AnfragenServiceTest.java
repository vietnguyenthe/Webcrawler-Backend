package de.awa.training.webcrawler.webcrawlerbackend.demo.ServiceTest;

import de.awa.training.webcrawler.entity.PostleitzahlenEntity;
import de.awa.training.webcrawler.repository.*;
import de.awa.training.webcrawler.services.AnfragenService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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

    @Test
    public void holePLZidTEST(){
        MockitoAnnotations.initMocks(this);
        List<PostleitzahlenEntity> liste = new ArrayList<>();
        liste.add(new PostleitzahlenEntity(1,"51570"));
        liste.add(new PostleitzahlenEntity(2,"52152"));
        liste.add(new PostleitzahlenEntity(3,"53804"));
        Mockito.when(plzRepository.findAll()).thenReturn(liste);
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







}
