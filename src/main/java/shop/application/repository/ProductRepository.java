package shop.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.application.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
