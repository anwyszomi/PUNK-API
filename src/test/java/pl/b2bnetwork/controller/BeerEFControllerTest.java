package pl.b2bnetwork.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.b2bnetwork.BeerRepositoryTest;
import pl.b2bnetwork.domain.Beer;
import pl.b2bnetwork.service.BeerService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class BeerEFControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private BeerEFController beerEFController;

    @Mock
    private BeerService beerService;

    private List<Beer> beerList = new ArrayList<>();
    private Set<Beer> beerSet = new HashSet<>();

    private BeerRepositoryTest repositoryTest = new BeerRepositoryTest();

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(beerEFController).build();

        beerList.add(Beer.builder().name("Buzz").build());

        beerSet.add(repositoryTest.beersListForTest().get(0));
    }

    @Test
    public void findAll() throws Exception {
        when(beerService.findAll()).thenReturn(beerList);
        mockMvc.perform(MockMvcRequestBuilders.get("/findAllBeers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("displayAllBeers"))
                .andExpect(MockMvcResultMatchers.view().name("displayAllBeers"));
    }

    @Test
    public void showFormBeerWithName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/showFormBeerWithName"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().attribute("beer", new Beer()))
                .andExpect(model().attribute("beerName", "active"))
                .andExpect(view().name("nameForm"))
                .andExpect(MockMvcResultMatchers.view().name("nameForm"));
    }

    @Test
    public void findBeerWithName() throws Exception {
        when(beerService.findByName("Buzz")).thenReturn(beerList);
        mockMvc.perform(MockMvcRequestBuilders.get("/findBeerWithName?name=Buzz"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().attribute("beer", beerList))
                .andExpect(model().attribute("name", "Buzz"))
                .andExpect(model().attribute("beerName", "active"))
                .andExpect(view().name("beerWithName"));
    }


    @Test
    public void showAbvForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/showAbvForm"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().attribute("beer", new Beer()))
                .andExpect(model().attribute("abvShow", "active"))
                .andExpect(view().name("abvForm"))
                .andExpect(MockMvcResultMatchers.view().name("abvForm"));
    }

    @Test
    public void findBeerWithAbv() throws Exception {
        when(beerService.findByAlcoholByVolume(4.4)).thenReturn(beerSet);
        mockMvc.perform(MockMvcRequestBuilders.get("/findBeerWithAbv?abv=4.4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().attribute("beer", beerSet))
                .andExpect(model().attribute("abv", 4.4))
                .andExpect(model().attribute("abvShow", "active"))
                .andExpect(MockMvcResultMatchers.view().name("beerWithAbv"));
    }


    @Test
    public void showMaltForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/showMaltForm"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().attribute("beer", new Beer()))
                .andExpect(model().attribute("maltShow", "active"))
                .andExpect(view().name("maltForm"))
                .andExpect(MockMvcResultMatchers.view().name("maltForm"));
    }

    @Test
    public void findBeerWithMalt() throws Exception {
        when(beerService.findBeerByMalt("Munich")).thenReturn(beerSet);
        mockMvc.perform(MockMvcRequestBuilders.get("/findBeerWithMalt?name=Munich"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().attribute("malt", beerSet))
                .andExpect(model().attribute("name", "Munich"))
                .andExpect(model().attribute("maltShow", "active"))
                .andExpect(MockMvcResultMatchers.view().name("beerWithMalt"));
    }

    @Test
    public void showHopsForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/showHopsForm"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().attribute("beer", new Beer()))
                .andExpect(model().attribute("hopsShow", "active"))
                .andExpect(view().name("hopsForm"))
                .andExpect(MockMvcResultMatchers.view().name("hopsForm"));
    }

    @Test
    public void findBeerWithHops() throws Exception {
        when(beerService.findBeerByHops("Fuggles")).thenReturn(beerSet);
        mockMvc.perform(MockMvcRequestBuilders.get("/findBeerWithHops?name=Fuggles"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().attribute("hops", beerSet))
                .andExpect(model().attribute("name", "Fuggles"))
                .andExpect(model().attribute("hopsShow", "active"))
                .andExpect(MockMvcResultMatchers.view().name("beerWithHops"));
    }
}