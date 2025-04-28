package ut.edu.koii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.koii.models.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
