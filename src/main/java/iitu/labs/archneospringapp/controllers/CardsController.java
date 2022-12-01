package iitu.labs.archneospringapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import iitu.labs.archneospringapp.models.Cards;
import iitu.labs.archneospringapp.models.Clients;
import iitu.labs.archneospringapp.repo.CardsRepository;
import iitu.labs.archneospringapp.repo.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Controller
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private ClientsRepository clientsRepository;

    @GetMapping("/login-user")
    public String getLoginPage(Model model) {
        model.addAttribute("title", "Login User Profile");
        return "login-user";
    }

    @PostMapping("/login-user")
    private String login(@RequestParam("email") String email, @RequestParam("password") String password) throws ParseException {
        Iterable<Cards> cards = cardsRepository.findAll();
        for (Cards card: cards) {
            if (Objects.equals(card.getEmail(), email)) {
                if (Objects.equals(card.getPassword(), password)) {
                    int id = Math.toIntExact(card.getId());
                    return "redirect:/card/" + id;
                }
            }
        };
        return "redirect:/cards";
    }

    @RequestMapping(value="/get-all")
    @ResponseBody
    public String getAll() {
        return convertObjectToJSON(clientsRepository.findAll());
    }

    public String convertObjectToJSON(Iterable<Clients> cards) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cards);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("/sign-user")
    public String getSignPage(Model model) {
        model.addAttribute("title", "Sign User Profile");
        return "sign-user";
    }

    @PostMapping("/sign-user")
    private String addUser(@RequestParam String password, @RequestParam String email, @RequestParam String first_name, @RequestParam String last_name, @RequestParam String bio, @RequestParam String birth, @RequestParam String university, @RequestParam String photo, @RequestParam int price, @RequestParam int experience) throws ParseException {
        Cards cards = new Cards(first_name, last_name, bio, birth, university, photo, email, password, price, experience);
        cardsRepository.save(cards);
        return "redirect:/cards";
    }

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
    private String userUpdate(@PathVariable(value = "id") long id, @RequestParam String first_name, @RequestParam String last_name, @RequestParam String bio, @RequestParam String birth, @RequestParam String university, @RequestParam String photo, @RequestParam int price, @RequestParam int experience, Model model) throws ParseException {
        Cards cards = cardsRepository.findById(id).orElseThrow();
        cards.setFirstName(first_name);
        cards.setLastName(last_name);
        cards.setBio(bio);
        cards.setBirth(birth);
        cards.setUniversity(university);
        cards.setPrice(price);
        cards.setPhoto(photo);
        cards.setExperience(experience);
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
