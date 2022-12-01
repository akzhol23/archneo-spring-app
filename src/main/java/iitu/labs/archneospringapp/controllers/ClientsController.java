package iitu.labs.archneospringapp.controllers;

import iitu.labs.archneospringapp.models.Clients;
import iitu.labs.archneospringapp.repo.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientsController {

    @Autowired
    private ClientsRepository clientsRepository;

    SCryptPasswordEncoder encoder = new SCryptPasswordEncoder(16384, 8, 4, 32, 64);

    @GetMapping("/sign")
    public String getSignPage(Model model) {
        model.addAttribute("title", "Sign");
        return "sign";
    }

    @PostMapping("/sign")
    private String clientsAdd(@RequestParam String email, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String password, @RequestParam String gender, Model model) {
        Clients clients = new Clients(email, firstName, lastName, encoder.encode(password), gender);
        clientsRepository.save(clients);
        return "redirect:/cards";
    }
}
