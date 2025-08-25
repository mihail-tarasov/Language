package ruMihailTarasov7.Language.Repository;

import org.springframework.data.repository.CrudRepository;
import ruMihailTarasov7.Language.Models.Post;

public interface PostRepository  extends CrudRepository<Post,Long> {
}
