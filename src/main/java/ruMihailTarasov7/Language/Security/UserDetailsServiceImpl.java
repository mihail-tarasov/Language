package ruMihailTarasov7.Language.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ruMihailTarasov7.Language.Models.User;
import ruMihailTarasov7.Language.Repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=== SPRING SECURITY ИЩЕТ ПОЛЬЗОВАТЕЛЯ ===");
        System.out.println("Ищем: " + username);

        User user = userRepository.findByUsername(username);

        if (user == null) {
            System.out.println("❌ ПОЛЬЗОВАТЕЛЬ НЕ НАЙДЕН: " + username);
            throw new UsernameNotFoundException("User not found: " + username);
        }

        System.out.println("✅ ПОЛЬЗОВАТЕЛЬ НАЙДЕН: " + user.getUsername());
        System.out.println("Пароль в БД: " + user.getPassword());
        System.out.println("Роль: " + user.getRole());

        // Создаём UserDetails для Spring Security
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER") // "USER" → "ROLE_USER"
                .build();
    }
}