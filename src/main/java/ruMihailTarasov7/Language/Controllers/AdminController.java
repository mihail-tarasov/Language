package ruMihailTarasov7.Language.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ruMihailTarasov7.Language.Models.User;
import ruMihailTarasov7.Language.Repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    // Панель управления
    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        List<User> allUsers = userRepository.findAll();
        model.addAttribute("users", allUsers);
        return "admin-dashboard";
    }

    // Просмотр карточек любого пользователя
    @GetMapping("/user/{userId}/posts")
    public String viewUserPosts(@PathVariable Long userId, Model model) {
        User user = userRepository.findById(userId).orElseThrow();
        model.addAttribute("post", user.getPosts());
        model.addAttribute("viewedUser", user);
        return "admin-user-posts";
    }

    // Блокировка пользователя
    @PostMapping("/user/{userId}/block")
    public String blockUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setEnabled(false); // 👈 Добавить поле enabled в User
        userRepository.save(user);
        return "redirect:/admin/dashboard";
    }

    // Разблокировка пользователя
    @PostMapping("/user/{userId}/unblock")
    public String unblockUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setEnabled(true);
        userRepository.save(user);
        return "redirect:/admin/dashboard";
    }
}
