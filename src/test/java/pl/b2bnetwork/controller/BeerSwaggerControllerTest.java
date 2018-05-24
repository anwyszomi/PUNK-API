package pl.b2bnetwork.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.b2bnetwork.BeerRepositoryTest;
import pl.b2bnetwork.domain.Beer;
import pl.b2bnetwork.service.BeerSwaggerService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BeerSwaggerControllerTest {

    Set<String> beerSet = new HashSet<>();

    @InjectMocks
    private BeerSwaggerController beerSwaggerController;

    @Mock
    private BeerSwaggerService beerSwaggerService;

    private MockMvc mockMvc;

    private BeerRepositoryTest repositoryTest = new BeerRepositoryTest();

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(beerSwaggerController).build();
        beerSet.add("Buzz");
    }

    @Test
    public void allBeers() throws Exception {
        when(beerSwaggerService.findAll()).thenReturn(repositoryTest.beersListForTest());
        mockMvc.perform(MockMvcRequestBuilders.get("/showAllBeers"))
                .andExpect(MockMvcResultMatchers.content().json(repositoryTest.convert(repositoryTest.beersListForTest())));
    }

    @Test
    public void findByName() throws Exception {
        List<Beer> beers = new ArrayList<>();
        Beer b = new Beer();
        beers.add(b);
        when(beerSwaggerService.findByName("buzz")).thenReturn(beers);
        mockMvc.perform(MockMvcRequestBuilders.get("/findByName").param("name", "buzz")).
                andExpect(MockMvcResultMatchers.content().json("[{\"id\":0,\"name\":null,\"abv\":null,\"ibu\":0,\"ebc\":0,\"srm\":0,\"ph\":0.0,\"volume\":null,\"ingredients\":null,\"attenuation_level\":0,\"boil_volume\":null}]"));
    }

    @Test
    public void finByAbv() throws Exception {
        when(beerSwaggerService.findByAlcoholByVolume(4.4)).thenReturn(beerSet);
        mockMvc.perform(MockMvcRequestBuilders.get("/findByAlcoholByVolume").param("alcoholByVolume", "4.4"))
                .andExpect(MockMvcResultMatchers.content().string("[\"Buzz\"]"));
    }

    @Test
    public void finByMalt() throws Exception {
        when(beerSwaggerService.findBeerByMalt("Munich")).thenReturn(beerSet);
        mockMvc.perform(MockMvcRequestBuilders.get("/findByMalt").param("malt", "Munich"))
                .andExpect(MockMvcResultMatchers.content().string("[\"Buzz\"]"));
    }

    @Test
    public void finByHops() throws Exception {
        when(beerSwaggerService.findBeerByHops("Fug")).thenReturn(beerSet);
        mockMvc.perform(MockMvcRequestBuilders.get("/findByHops").param("hops", "Fug"))
                .andExpect(MockMvcResultMatchers.content().string("[\"Buzz\"]"));
    }


}