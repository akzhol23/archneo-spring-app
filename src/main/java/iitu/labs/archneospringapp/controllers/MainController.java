package iitu.labs.archneospringapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("title", "Main page");
        return "index";
    }

    @GetMapping("/sign")
    public String getSignPage(Model model) {
        model.addAttribute("title", "Main page");
        return "sign";
    }

}
