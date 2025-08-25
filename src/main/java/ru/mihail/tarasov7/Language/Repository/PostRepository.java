package ru.mihail.tarasov7.Language.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.mihail.tarasov7.Language.Models.Post;

public interface PostRepository  extends CrudRepository<Post,Long> {
}
