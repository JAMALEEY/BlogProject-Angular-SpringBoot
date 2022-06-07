package ma.youcode.firo.repository;

import ma.youcode.firo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long > {
}
