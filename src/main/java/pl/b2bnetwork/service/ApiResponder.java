package pl.b2bnetwork.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.b2bnetwork.domain.Beer;

import java.util.Arrays;
import java.util.List;

@Component
public class ApiResponder {

    private static final String url = "https://api.punkapi.com/v2/beers";

    public List<Beer> result() {
        RestTemplate restTemplate = new RestTemplate();
        Beer[] obj = restTemplate.getForObject(url, Beer[].class);
        return Arrays.asList(obj);
    }
}
