package ut.edu.koii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.koii.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);


    // Thêm phương thức kiểm tra email
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findUserByUsername(String username);
}
