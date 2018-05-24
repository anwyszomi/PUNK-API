package pl.b2bnetwork.service;

import pl.b2bnetwork.domain.Beer;

import java.util.List;
import java.util.Set;

public interface BeerSwaggerService {
    List<Beer> findAll();

    List<Beer> findByName(String name);

    Set<String> findByAlcoholByVolume(Double alcoholByVolume);

    Set<String> findBeerByMalt(String malt);

    Set<String> findBeerByHops(String hops);
}
