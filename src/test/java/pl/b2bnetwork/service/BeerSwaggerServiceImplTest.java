package pl.b2bnetwork.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import pl.b2bnetwork.BeerRepositoryTest;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(value = MockitoJUnitRunner.class)
@SpringBootTest
public class BeerSwaggerServiceImplTest {

    BeerRepositoryTest beerRepositoryTest = new BeerRepositoryTest();

    @InjectMocks
    private BeerSwaggerServiceImpl beerSwaggerService;

    @Mock
    private ApiResponder responder;


    @Before
    public void init() {
        when(responder.result()).thenReturn(beerRepositoryTest.beersListForTest());
    }

    @Test
    public void result() {
        assertEquals(2, beerSwaggerService.findAll().size());
    }

    @Test
    public void findByName() {
        String str = "[Beer:  name='Buzz, alcohol by volume=4.4, attenuationLevel=85, volume=20 , " +
                "boilVolume=10, ingredients=Ingredients: malt=[Munich:3.3, Lager:3.3, Malt:4.3, Caramlt:2.1], " +
                "hops=[Fuggles:3.3, Amarillo:4.1, Cascade:4.3, Motueko:2.1]]";
        assertEquals(str, beerSwaggerService.findByName("Buzz").toString());
    }

    @Test
    public void findByNameFalse() {
        assertEquals(0, beerSwaggerService.findByName("bzz").size());
    }

    @Test
    public void findByAbvContains() {
        assertThat(beerSwaggerService.findByAlcoholByVolume(4.4), containsInAnyOrder("Buzz"));
    }

    @Test
    public void findByAbvSize() {
        assertEquals(1, beerSwaggerService.findByAlcoholByVolume(4.4).size());
    }

    @Test
    public void findBeerByMaltSmallLetter() {
        assertEquals(1, beerSwaggerService.findBeerByMalt("lager").size());
    }

    @Test
    public void findByMaltIsEmpty() {
        assertTrue(beerSwaggerService.findBeerByMalt("bzz").isEmpty());
    }


    @Test
    public void findBeerByHopsSmallLetter() {
        assertEquals(1, beerSwaggerService.findBeerByHops("fuggles").size());
    }

    @Test
    public void findByHopsIsEmpty() {
        assertTrue(beerSwaggerService.findBeerByHops("fug").isEmpty());
    }
}