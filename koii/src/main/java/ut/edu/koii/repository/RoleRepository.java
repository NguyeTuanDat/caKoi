package ut.edu.koii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.koii.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
