package ruMihailTarasov7.Language.Security;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;
import ruMihailTarasov7.Language.Models.User;
@Data
public class RegistrationForm {
    private String username;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password));
    }
}
