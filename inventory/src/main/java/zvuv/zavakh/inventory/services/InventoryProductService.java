package zvuv.zavakh.inventory.services;

import zvuv.zavakh.inventory.domain.InventoryProduct;
import zvuv.zavakh.inventory.web.dto.InventoryProductDto;

import java.util.List;

public interface InventoryProductService {

    void add(InventoryProductDto inventoryProductDto);
    void update(InventoryProduct inventoryProduct);
    List<InventoryProduct> findByProductIds(List<Long> productIds);
}
