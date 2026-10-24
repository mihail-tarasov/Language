package ruMihailTarasov7.Language.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ruMihailTarasov7.Language.Models.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
