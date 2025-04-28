package ut.edu.koii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.koii.models.WaterParameter;

import java.util.List;

public interface WaterParameterRepository extends JpaRepository<WaterParameter, Long> {
    List<WaterParameter> findByPondIdOrderByMeasuredAtDesc(Long pondId);
}

