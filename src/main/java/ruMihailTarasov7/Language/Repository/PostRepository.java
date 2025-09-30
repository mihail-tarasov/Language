package ruMihailTarasov7.Language.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ruMihailTarasov7.Language.Models.Post;

public interface PostRepository  extends JpaRepository<Post,Long> {
}
