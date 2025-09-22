package ruMihailTarasov7.Language.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ruMihailTarasov7.Language.Models.User;
import ruMihailTarasov7.Language.Repository.UserRepository;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
            User user=userRepository.findByUsername(username);
            if(user!=null) return user;
            throw new UsernameNotFoundException("User '"+username+"' not found");
        };
    }
}
