package ut.edu.koii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.koii.models.Pond;

public interface PondRepository extends JpaRepository<Pond, Long> {
}