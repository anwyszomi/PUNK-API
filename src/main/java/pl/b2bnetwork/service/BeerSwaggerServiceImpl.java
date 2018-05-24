package pl.b2bnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.b2bnetwork.domain.Beer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BeerSwaggerServiceImpl implements BeerSwaggerService {

    @Autowired
    private ApiResponder responder;

    @Override
    public List<Beer> findAll() {
        return responder.result();
    }

    @Override
    public List<Beer> findByName(String name) {
        return responder.result().stream()
                .filter(f -> f.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }

    @Override
    public Set<String> findByAlcoholByVolume(Double alcoholByVolume) {
        return responder.result().stream().
                filter(beer -> beer.getAbv().equals(alcoholByVolume)).map(be -> be.getName()).
                collect(Collectors.toSet());
    }

    @Override
    public Set<String> findBeerByMalt(String malt) {
        return responder.result().stream()
                .filter(beer -> beer.getIngredients()
                        .getMalt()
                        .stream()
                        .anyMatch(ma -> ma.getName().equalsIgnoreCase(malt)))
                .map(be -> be.getName())
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> findBeerByHops(String hops) {
        return responder.result().stream()
                .filter(beer -> beer.getIngredients().getHops().stream()
                        .anyMatch(ho -> ho.getName().equalsIgnoreCase(hops)))
                .map(be -> be.getName())
                .collect(Collectors.toSet());
    }
}
