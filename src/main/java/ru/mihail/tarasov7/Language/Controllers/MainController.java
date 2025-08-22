package ru.mihail.tarasov7.Language.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String firstPage(Model model){
        model.addAttribute("title","Главная страница");
        return "firstPage";
    }
}
