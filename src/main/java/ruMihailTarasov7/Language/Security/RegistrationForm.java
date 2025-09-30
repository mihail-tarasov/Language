package ruMihailTarasov7.Language.Security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ruMihailTarasov7.Language.Models.User;

@Data
public class RegistrationForm {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder) {
        System.out.println("=== В МЕТОДЕ toUser ===");
        System.out.println("Получен пароль для кодирования: " + this.password);

        User user = new User();
        user.setUsername(username);

        String encoded = passwordEncoder.encode(password);
        System.out.println("Пароль после кодирования: " + encoded);

        user.setPassword(encoded); // Важно: передаём ЗАКОДИРОВАННЫЙ пароль!
        user.setRole("USER");

        return user;
    }
}