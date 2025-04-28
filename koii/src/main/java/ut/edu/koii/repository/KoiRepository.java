package ut.edu.koii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.koii.models.Koi;
import ut.edu.koii.models.Pond;

import java.util.List;

public interface KoiRepository extends JpaRepository<Koi, Long> {
    List<Koi> findByPond(Pond pond);

    List<Koi> findByPondId(Long pondId);
    // Sửa lại tên metho
}
