package pl.b2bnetwork.service;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import pl.b2bnetwork.BeerRepositoryTest;

import java.util.ArrayList;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(value = MockitoJUnitRunner.class)
@SpringBootTest
public class BeerServiceImplTest {

    BeerRepositoryTest beerRepositoryTest = new BeerRepositoryTest();

    @InjectMocks
    private BeerServiceImpl beerService;

    @Mock
    private ApiResponder responder;

    @Before
    public void init() {
        when(responder.result()).thenReturn(beerRepositoryTest.beersListForTest());
    }

    @Test
    public void result() {
        assertEquals(2, beerService.findAll().size());
    }

    @Test
    public void findByName() {
        String str = "[Beer:  name='Buzz, alcohol by volume=4.4, attenuationLevel=85, volume=20 , " +
                "boilVolume=10, ingredients=Ingredients: malt=[Munich:3.3, Lager:3.3, Malt:4.3, Caramlt:2.1], " +
                "hops=[Fuggles:3.3, Amarillo:4.1, Cascade:4.3, Motueko:2.1]]";
        assertEquals(str, beerService.findByName("Buzz").toString());
    }

    @Test
    public void findByNameFalse() {
        assertEquals(0, beerService.findByName("bzz").size());
    }

    @Test
    public void findByAbvListNotEmpty() {
        assertThat(beerService.findByAlcoholByVolume(4.4), not(IsEmptyCollection.empty()));
    }

    @Test
    public void findByAbvContains() {
        assertThat(beerService.findByAlcoholByVolume(4.4), containsInAnyOrder(beerRepositoryTest.beersListForTest().get(0)));
    }

    @Test
    public void findByAbvSize() {
        assertEquals(1, beerService.findByAlcoholByVolume(4.4).size());
    }

    @Test
    public void findByAbvIsEmpty() {
        assertTrue(beerService.findByAlcoholByVolume(4.5).isEmpty());
    }

    @Test
    public void findByAbvIsEmpty2() {
        assertTrue(beerService.findByAlcoholByVolume(4.3).isEmpty());
    }

    @Test
    public void findByAbvEmptyInput() {
        assertEquals(new HashSet<String>(), beerService.findByAlcoholByVolume(null));
    }

    @Test
    public void findBeerByMaltSmallLetter() {
        assertEquals(1, beerService.findBeerByMalt("lager").size());
    }

    @Test
    public void findBeerByMaltBigLetter() {
        assertEquals(1, beerService.findBeerByMalt("Lager").size());
    }

    @Test
    public void findByMaltIsEmpty() {
        assertTrue(beerService.findBeerByMalt("bzz").isEmpty());
    }

    @Test
    public void findBeerByHopsBigLetter() {
        assertEquals(1, beerService.findBeerByHops("Fuggles").size());
    }

    @Test
    public void findBeerByHopsSmallLetter() {
        assertEquals(1, beerService.findBeerByHops("fuggles").size());
    }

    @Test
    public void findByHopsIsEmpty() {
        assertTrue(beerService.findBeerByHops("fug").isEmpty());
    }
}

