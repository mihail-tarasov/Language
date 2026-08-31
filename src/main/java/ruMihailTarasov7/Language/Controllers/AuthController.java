package ruMihailTarasov7.Language.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")  // должна быть отдельная страница!
    public String login() {
        return "login";
    }


}
