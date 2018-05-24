package pl.b2bnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.b2bnetwork.domain.Beer;
import pl.b2bnetwork.domain.Hops;
import pl.b2bnetwork.domain.Malt;
import pl.b2bnetwork.service.BeerService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BeerEFController {

    @Autowired
    private BeerService beerService;

    @RequestMapping("/start")
    public String start() {
        return "start";
    }

    @RequestMapping("/findAllBeers")
    public String findAll(Model model) {
        model.addAttribute("beer", beerService.findAll());
        model.addAttribute("all", "active");
        return "displayAllBeers";
    }

    @RequestMapping("/showFormBeerWithName")
    public String showFormBeerWithName(Model model) {
        model.addAttribute("beer", new Beer());
        model.addAttribute("beerName", "active");
        return "nameForm";
    }

    @RequestMapping("/findBeerWithName")
    public String findBeerWithName(Model model, @RequestParam(value = "name") String name) {
        model.addAttribute("beer", beerService.findByName(name));
        model.addAttribute("name", name);
        model.addAttribute("beerName", "active");
        return "beerWithName";
    }

    @RequestMapping("/showAbvForm")
    public String showAbvForm(Model model) {
        model.addAttribute("beer", new Beer());
        model.addAttribute("abvShow", "active");
        return "abvForm";
    }

    @RequestMapping("/findBeerWithAbv")
    public String findBeerWithAbv(Model model, @RequestParam(value = "abv") Double alcoholByVolume) {
        model.addAttribute("beer", beerService.findByAlcoholByVolume(alcoholByVolume));
        model.addAttribute("abv", alcoholByVolume);
        model.addAttribute("abvShow", "active");
        return "beerWithAbv";
    }

    @RequestMapping("/showMaltForm")
    public String showMaltForm(Model model) {
        model.addAttribute("beer", new Beer());
        model.addAttribute("maltShow", "active");
        return "maltForm";
    }

    @RequestMapping("/findBeerWithMalt")
    public String findBeerWithMalt(Model model, @RequestParam(value = "name") String malt) {
        model.addAttribute("malt", beerService.findBeerByMalt(malt));
        model.addAttribute("name", malt);
        model.addAttribute("maltShow", "active");
        return "beerWithMalt";
    }

    @RequestMapping("/showHopsForm")
    public String showHopsForm(Model model) {
        model.addAttribute("beer", new Beer());
        model.addAttribute("hopsShow", "active");
        return "hopsForm";
    }

    @RequestMapping("/findBeerWithHops")
    public String findBeerWithHops(Model model, @RequestParam(value = "name") String hops) {
        model.addAttribute("hops", beerService.findBeerByHops(hops));
        model.addAttribute("name", hops);
        model.addAttribute("hopsShow", "active");
        return "beerWithHops";
    }

}
