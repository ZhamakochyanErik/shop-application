package shop.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.application.model.Product;
import shop.application.model.ProductRate;

import java.util.List;

public interface ProductRateRepository extends JpaRepository<ProductRate, Long> {

    List<ProductRate> findAllByProduct(Product product);
}
