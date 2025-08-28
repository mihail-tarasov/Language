package ruMihailTarasov7.Language.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ruMihailTarasov7.Language.Models.Post;
import ruMihailTarasov7.Language.Repository.PostRepository;

@Controller
public class HomeController {
    @Autowired
    private PostRepository postRepository;
    @GetMapping("/home")
    public String homePage(Model model){
        Iterable<Post> posts= postRepository.findAll();
        model.addAttribute("posts",posts);
        return "homePage";
    }
}
