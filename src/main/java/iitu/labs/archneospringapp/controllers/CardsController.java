package iitu.labs.archneospringapp.controllers;

import iitu.labs.archneospringapp.models.Cards;
import iitu.labs.archneospringapp.repo.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @GetMapping("/cards")
    public String getCards(Model model) {
        Iterable<Cards> cards = cardsRepository.findAll();
        model.addAttribute("title", "Cards page");
        model.addAttribute("cards", cards);
        return "cards";
    }

    @GetMapping("/card/{id}")
    public String userDetails(@PathVariable(value = "id") long id, Model model) {
        if(!cardsRepository.existsById(id)) {
            return "redirect:/cards";
        }
        Optional<Cards> card = cardsRepository.findById(id);
        ArrayList<Cards> res = new ArrayList<>();
        card.ifPresent(res::add);
        model.addAttribute("cards", res);
        model.addAttribute("title", "User page");
        return "user";
    }
}
