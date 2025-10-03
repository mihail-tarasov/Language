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
    public CommandLineRunner initAdmin(UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            if (userRepo.findByUsername("admin") == null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("262614"));
                admin.setRole("ADMIN");
                userRepo.save(admin);
                System.out.println("=== АДМИН ДЛЯ НАШЕЙ КОМАНДЫ ===");
                System.out.println("Логин: admin");
                System.out.println("Пароль: 262614");
                System.out.println("Команда: Миша + Дипп = 💪");
            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/css/**", "/js/**", "/login", "/register").permitAll()
                        .requestMatchers("/home", "/home/**").hasAnyRole("USER","ADMIN")
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
