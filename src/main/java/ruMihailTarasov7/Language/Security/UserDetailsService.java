package ruMihailTarasov7.Language.Security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException;
}
