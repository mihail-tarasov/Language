package ruMihailTarasov7.Language.Controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ruMihailTarasov7.Language.Models.User;
import ruMihailTarasov7.Language.Repository.UserRepository;
import ruMihailTarasov7.Language.Security.RegistrationForm;

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
        System.out.println("=== НАЧАЛО РЕГИСТРАЦИИ ===");
        System.out.println("Получены данные: " + form.getUsername() + ", " + form.getPassword());

        // ДОБАВИЛИ ОТЛАДКУ ПАРОЛЕЙ!
        System.out.println("=== КОДИРОВАНИЕ ПАРОЛЯ ===");
        String encodedPassword = passwordEncoder.encode(form.getPassword());
        System.out.println("Исходный пароль: " + form.getPassword());
        System.out.println("Закодированный пароль: " + encodedPassword);

        // Проверяем, нет ли уже пользователя с таким именем
        if (userRepository.findByUsername(form.getUsername()) != null) {
            System.out.println("ОШИБКА: Пользователь " + form.getUsername() + " уже существует!");
            model.addAttribute("error", "Имя пользователя уже занято");
            return "registration";
        }

        try {
            // Создаем пользователя
            User user = form.toUser(passwordEncoder);
            System.out.println("Создан объект User: " + user.getUsername());
            System.out.println("Пароль в User объекте: " + user.getPassword()); // ДОБАВИЛИ!

            // Сохраняем в базу
            User savedUser = userRepository.save(user);
            System.out.println("УСПЕХ: Пользователь сохранен с ID: "); // ИСПРАВИЛИ!

            // Дополнительная проверка - читаем из базы
            User fromDb = userRepository.findByUsername(form.getUsername());
            if (fromDb != null) {
                System.out.println("ПОДТВЕРЖДЕНИЕ: Пользователь найден в БД - " + fromDb.getUsername());
                System.out.println("Пароль в БД: " + fromDb.getPassword()); // ДОБАВИЛИ!
            } else {
                System.out.println("ПРЕДУПРЕЖДЕНИЕ: Пользователь не найден в БД после сохранения!");
            }

            return "redirect:/login?registered";

        } catch (Exception e) {
            System.out.println("ОШИБКА СОХРАНЕНИЯ: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Ошибка регистрации: " + e.getMessage());
            return "registration";
        }
    }
}