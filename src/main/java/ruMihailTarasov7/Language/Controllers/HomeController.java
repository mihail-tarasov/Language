package ruMihailTarasov7.Language.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ruMihailTarasov7.Language.Models.Post;
import ruMihailTarasov7.Language.Models.User;
import ruMihailTarasov7.Language.Repository.PostRepository;
import ruMihailTarasov7.Language.Repository.UserRepository;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    //@GetMapping("/home")
    //public String homePage(Model model){
    //    Iterable<Post> posts= postRepository.findAll();
    //    model.addAttribute("posts",posts);
    //    return "homePage2";
    //}
    @GetMapping("/home")
    public String homePage(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("posts", postRepository.findByUser(user));
        return "homePage2";
    }
    @GetMapping("/home/blog/add")
    public String blogAdd( Model model){

       return "blog-add";
    }
    @PostMapping("/home/blog/add")
    public String blogPostAdd(@RequestParam String word, @RequestParam String translation, @RequestParam String full_text, Model model){
        Post post=new Post(word,translation,full_text);
        postRepository.save(post);
        return "redirect:/home";
    }
    @GetMapping("/home/blog/{id}")
    public String blogDetails(@PathVariable(value="id")long id, Model model){

        Optional<Post> post=postRepository.findById(id);
        ArrayList<Post> res=new ArrayList<>();
        post.ifPresent(res :: add);
        model.addAttribute("post",res);
        return "blog-details";
    }
    @GetMapping("/home/blog/{id}/edit")
    public String blogEdit(@PathVariable(value="id")long id,Model model){
        if(!postRepository.existsById(id)){
            return "homePage2";
        }
        Optional<Post>post=postRepository.findById(id);
        ArrayList<Post> res=new ArrayList<>();
        post.ifPresent(res :: add);
        model.addAttribute("post",res);
        return "blog-edit";
    }
    @PostMapping("/home/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value="id")long id,@RequestParam String word,@RequestParam String translation,@RequestParam String full_text, Model model){
        Post post=postRepository.findById(id).orElseThrow();
        post.setWord(word);
        post.setTranslation(translation);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "homePage2";
    }
    @PostMapping("/home/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value="id")long id, Model model){
        Post post=postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/home";
    }

}


