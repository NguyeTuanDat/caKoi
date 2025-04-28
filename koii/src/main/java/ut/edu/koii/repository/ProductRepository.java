package ut.edu.koii.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ut.edu.koii.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
