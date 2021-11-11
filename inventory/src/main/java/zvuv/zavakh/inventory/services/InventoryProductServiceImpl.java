package zvuv.zavakh.inventory.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zvuv.zavakh.inventory.domain.InventoryProduct;
import zvuv.zavakh.inventory.repositories.InventoryProductRepository;
import zvuv.zavakh.inventory.web.dto.InventoryProductDto;

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
}
