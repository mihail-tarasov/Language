package ruMihailTarasov7.Language.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AbstractController {
    @GetMapping("/site/abstract")
    public String abstractPage(Model model) {
        return "abstract";
    }
    
    
}





