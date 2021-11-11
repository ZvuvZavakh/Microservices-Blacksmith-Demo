package zvuv.zavakh.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zvuv.zavakh.inventory.domain.InventoryProduct;

@Repository
public interface InventoryProductRepository extends JpaRepository<InventoryProduct, Long> {

    InventoryProduct findByProductId(Long productId);
}
