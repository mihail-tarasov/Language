package ruMihailTarasov7.Language.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("title","Это страничка с твоими карточками!");
        return "homePage";
    }
}
