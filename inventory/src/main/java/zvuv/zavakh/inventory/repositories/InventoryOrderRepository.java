package zvuv.zavakh.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zvuv.zavakh.inventory.domain.InventoryOrder;

@Repository
public interface InventoryOrderRepository extends JpaRepository<InventoryOrder, Long> {
}
