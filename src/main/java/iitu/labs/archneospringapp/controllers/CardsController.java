package iitu.labs.archneospringapp.controllers;

import iitu.labs.archneospringapp.models.Cards;
import iitu.labs.archneospringapp.repo.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @GetMapping("/cards")
    public String getCards(Model model) {
        Iterable<Cards> cards = cardsRepository.findAll();
        model.addAttribute("cards", cards);
        return "cards";
    }
}
