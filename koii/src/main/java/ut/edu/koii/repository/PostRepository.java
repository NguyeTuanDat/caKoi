package ut.edu.koii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.koii.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
