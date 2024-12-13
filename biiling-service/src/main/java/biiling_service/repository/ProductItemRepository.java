package biiling_service.repository;

import biiling_service.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem , Long> {
}
