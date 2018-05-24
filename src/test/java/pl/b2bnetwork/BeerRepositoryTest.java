package pl.b2bnetwork;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import pl.b2bnetwork.domain.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BeerRepositoryTest {

    public List<Beer> beersListForTest() {
        Amount amount1 = new Amount.Builder()
                .value("3.3")
                .unit("kg")
                .build();
        Amount amount2 = new Amount.Builder()
                .value("4.1")
                .unit("kg")
                .build();

        Malt malt1 = new Malt.Builder()
                .amount(amount1)
                .name("Munich")
                .build();
        Malt malt2 = new Malt.Builder()
                .amount(amount1)
                .name("Lager")
                .build();

        List<Malt> maltList = new ArrayList<>();
        maltList.add(malt1);
        maltList.add(malt2);

        Hops hops1 = new Hops.Builder()
                .amount(amount1)
                .name("Fuggles")
                .build();

        Hops hops2 = new Hops.Builder()
                .amount(amount2)
                .name("Amarillo")
                .build();

        List<Hops> hopsList = new ArrayList<>();
        hopsList.add(hops1);
        hopsList.add(hops2);

        Ingredients ingredients1 = new Ingredients.Builder()
                .hops(hopsList)
                .malt(maltList)
                .build();

        Volume volume1 = new Volume.Builder()
                .value(20)
                .unit("liters")
                .build();

        BoilVolume boilVolume1 = new BoilVolume.Builder()
                .value(10)
                .unit("liters")
                .build();

        Beer beer1 = new Beer.Builder()
                .id(1)
                .name("Buzz")
                .abv(4.4)
                .ibu(60)
                .ebc(20)
                .srm(10)
                .ph(4.4)
                .attenuationLevel(85)
                .volume(volume1)
                .boilVolume(boilVolume1)
                .ingredients(ingredients1)
                .build();

        Amount amount3 = new Amount.Builder()
                .value("4.3")
                .unit("kg")
                .build();
        Amount amount4 = new Amount.Builder()
                .value("2.1")
                .unit("kg")
                .build();

        Malt malt3 = new Malt.Builder()
                .amount(amount3)
                .name("Malt")
                .build();
        Malt malt4 = new Malt.Builder()
                .amount(amount4)
                .name("Caramlt")
                .build();

        List<Malt> maltList1 = new ArrayList<>();
        maltList.add(malt3);
        maltList.add(malt4);

        Hops hops3 = new Hops.Builder()
                .amount(amount3)
                .name("Cascade")
                .build();

        Hops hops4 = new Hops.Builder()
                .amount(amount4)
                .name("Motueko")
                .build();

        List<Hops> hopsList1 = new ArrayList<>();
        hopsList.add(hops3);
        hopsList.add(hops4);

        Ingredients ingredients2 = new Ingredients.Builder()
                .hops(hopsList1)
                .malt(maltList1)
                .build();

        Volume volume2 = new Volume.Builder()
                .value(25)
                .unit("liters")
                .build();

        BoilVolume boilVolume2 = new BoilVolume.Builder()
                .value(21)
                .unit("liters")
                .build();

        Beer beer2 = new Beer.Builder()
                .id(2)
                .name("DarkLager")
                .abv(6.3)
                .ibu(55)
                .ebc(30)
                .srm(15)
                .ph(4.4)
                .attenuationLevel(80)
                .volume(volume2)
                .boilVolume(boilVolume2)
                .ingredients(ingredients2)
                .build();
        List<Beer> beerList = new ArrayList<>();
        beerList.add(beer1);
        beerList.add(beer2);

        return beerList;
    }

    public String convert(List<Beer> beerList) {

        ObjectMapper mapper = new ObjectMapper();

        String jsonInString = "";

        try {
            jsonInString = mapper.writeValueAsString(beerList);
            System.out.println(jsonInString);

            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(beerList);
            System.out.println(jsonInString);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonInString;
    }

}
