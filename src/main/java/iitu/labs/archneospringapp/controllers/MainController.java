package iitu.labs.archneospringapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import iitu.labs.archneospringapp.models.Clients;
import iitu.labs.archneospringapp.repo.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("title", "Main page");
        return "index";
    }
}
