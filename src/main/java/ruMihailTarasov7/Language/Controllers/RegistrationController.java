package ruMihailTarasov7.Language.Controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ruMihailTarasov7.Language.Repository.UserRepository;
import ruMihailTarasov7.Language.Security.RegistrationForm;
import ruMihailTarasov7.Language.Models.User.*;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form, Model model) {
        System.out.println("=== РЕГИСТРАЦИЯ ===");
        System.out.println("Username: " + form.getUsername());
        System.out.println("Password: " + form.getPassword());

        // Проверяем, нет ли уже такого пользователя
        if (userRepository.findByUsername(form.getUsername()) != null) {
            System.out.println("ОШИБКА: Пользователь уже существует!");
            model.addAttribute("error", "Username already exists");
            return "registration";
        }

        try {
            // Сохраняем пользователя
            userRepository.save(form.toUser(passwordEncoder));
            System.out.println("УСПЕХ: Пользователь сохранён!");

            // Редирект на логин, а не на защищённую страницу
            return "redirect:/login?registered";

        } catch (Exception e) {
            System.out.println("ОШИБКА СОХРАНЕНИЯ: " + e.getMessage());
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "registration";
        }
    }
}
