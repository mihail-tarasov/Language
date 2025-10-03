package ruMihailTarasov7.Language.Security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import ruMihailTarasov7.Language.Models.User;
import ruMihailTarasov7.Language.Repository.UserRepository;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/css/**", "/js/**", "/login", "/register").permitAll()
                        .requestMatchers("/home", "/home/**").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN") // 👈 ТОЛЬКО ADMIN
                        .anyRequest().authenticated()  // всё остальное требует авторизации
                )
                //.csrf(csrf -> csrf.disable())
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .formLogin(form -> form
                        .loginPage("/login")          // отдельная страница логина
                        .defaultSuccessUrl("/home")
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )
                .build();
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
