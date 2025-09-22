package ruMihailTarasov7.Language.Repository;

import org.springframework.data.repository.CrudRepository;
import ruMihailTarasov7.Language.Models.User;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
