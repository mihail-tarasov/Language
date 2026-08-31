package ruMihailTarasov7.Language.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ruMihailTarasov7.Language.Models.Post;
import ruMihailTarasov7.Language.Models.User;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    // Найти все посты пользователя
    List<Post> findByUser(User user);

    // Найти пост по ID и пользователю (для безопасности)
    Optional<Post> findByIdAndUser(Long id, User user);
}
