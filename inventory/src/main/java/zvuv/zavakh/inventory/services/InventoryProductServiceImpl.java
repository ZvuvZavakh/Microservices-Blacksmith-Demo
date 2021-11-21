package zvuv.zavakh.inventory.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zvuv.zavakh.inventory.domain.InventoryProduct;
import zvuv.zavakh.inventory.repositories.InventoryProductRepository;
import zvuv.zavakh.inventory.web.dto.InventoryProductDto;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoryProductServiceImpl implements InventoryProductService {

    private final InventoryProductRepository inventoryProductRepository;

    @Override
    public void add(InventoryProductDto inventoryProductDto) {
        InventoryProduct inventoryProduct =
                inventoryProductRepository.findByProductId(inventoryProductDto.getProductId());

        inventoryProduct.setAmount(inventoryProduct.getAmount() + 1);
        inventoryProductRepository.save(inventoryProduct);
    }

    @Override
    public List<InventoryProduct> findByProductIds(List<Long> productIds) {
        return inventoryProductRepository.findByProductIdIn(productIds);
    }

    @Override
    public void update(InventoryProduct inventoryProduct) {
        inventoryProductRepository.save(inventoryProduct);
    }
}
