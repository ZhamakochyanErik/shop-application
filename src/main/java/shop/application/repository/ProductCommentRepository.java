package shop.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.application.model.Product;
import shop.application.model.ProductComment;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {

    List<ProductComment> findAllByProduct(Product product);
}
