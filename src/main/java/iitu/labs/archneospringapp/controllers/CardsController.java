package iitu.labs.archneospringapp.controllers;

import iitu.labs.archneospringapp.models.Cards;
import iitu.labs.archneospringapp.models.Clients;
import iitu.labs.archneospringapp.repo.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
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
        if (isIdExist(id, model)) return "redirect:/cards";
        model.addAttribute("title", "User page");
        return "user";
    }

    @GetMapping("/card/{id}/edit")
    public String userEdit(@PathVariable(value = "id") long id, Model model) {
        if (isIdExist(id, model)) return "redirect:/cards";
        model.addAttribute("title", "Edit User Info");
        return "user-edit";
    }

    @PostMapping("/card/{id}/edit")
    private String userUpdate(@PathVariable(value = "id") long id, @RequestParam String first_name, @RequestParam String last_name, @RequestParam String bio, @RequestParam String birth, @RequestParam String university, @RequestParam int price, @RequestParam String experience, Model model) throws ParseException {
        Cards cards = cardsRepository.findById(id).orElseThrow();
        cards.setFirstName(first_name);
        cards.setLastName(last_name);
        cards.setBio(bio);
        cards.setBirth(birth);
        cards.setUniversity(university);
        cards.setPrice(price);
        cards.setExperience(Integer.parseInt(experience));
        cardsRepository.save(cards);
        model.addAttribute("title", "Edit User Info");
        return "redirect:/cards";
    }

    @PostMapping("/card/{id}/remove")
    private String userDelete(@PathVariable(value = "id") long id, Model model) throws ParseException {
        Cards cards = cardsRepository.findById(id).orElseThrow();
        cardsRepository.delete(cards);
        model.addAttribute("title", "Delete User Info");
        return "redirect:/cards";
    }

    private boolean isIdExist(@PathVariable("id") long id, Model model) {
        if(!cardsRepository.existsById(id)) {
            return true;
        }
        Optional<Cards> card = cardsRepository.findById(id);
        ArrayList<Cards> res = new ArrayList<>();
        card.ifPresent(res::add);
        model.addAttribute("cards", res);
        return false;
    }
}
