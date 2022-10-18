package iitu.labs.archneospringapp.controllers;

import iitu.labs.archneospringapp.models.Clients;
import iitu.labs.archneospringapp.repo.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientsController {

    @Autowired
    private ClientsRepository clientsRepository;

    @PostMapping("/sign")
    private String clientsAdd(@RequestParam String email, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String password, @RequestParam String gender, Model model) {
        Clients clients = new Clients(email, firstName, lastName, password, gender);
        clientsRepository.save(clients);
        return "redirect:/index";
    }
}
