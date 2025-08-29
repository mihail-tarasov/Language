package ruMihailTarasov7.Language.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ruMihailTarasov7.Language.Models.Post;
import ruMihailTarasov7.Language.Repository.PostRepository;

import java.util.ArrayList;
import java.util.Optional;

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
    @GetMapping("/home/add")
    public String homePageTitleAdd( Model model){
        return "homePageTitleAdd";
    }
    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String word, @RequestParam String translation, @RequestParam String full_text, Model model){
        Post post=new Post(word,translation,full_text);
        postRepository.save(post);
        return "blog-main";
    }
    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value="id")long id, Model model){
        if(!postRepository.existsById(id)){
            return "blog-main";
        }
        Optional<Post> post=postRepository.findById(id);
        ArrayList<Post> res=new ArrayList<>();
        post.ifPresent(res :: add);
        model.addAttribute("post",res);
        return "blog-details";
    }
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value="id")long id,Model model){
        if(!postRepository.existsById(id)){
            return "/blog-main";
        }
        Optional<Post>post=postRepository.findById(id);
        ArrayList<Post> res=new ArrayList<>();
        post.ifPresent(res :: add);
        model.addAttribute("post",res);
        return "blog-edit";
    }
    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value="id")long id,@RequestParam String word,@RequestParam String translation,@RequestParam String full_text, Model model){
        Post post=postRepository.findById(id).orElseThrow();
        post.setWord(word);
        post.setTranslation(translation);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "blog-main";
    }
    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value="id")long id, Model model){
        Post post=postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "blog-main";
    }
}


