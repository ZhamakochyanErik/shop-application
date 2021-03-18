package shop.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.application.model.ProductCategory;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {

}
