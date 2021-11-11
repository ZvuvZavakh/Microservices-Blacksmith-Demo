package zvuv.zavakh.inventory.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zvuv.zavakh.inventory.domain.InventoryOrder;
import zvuv.zavakh.inventory.domain.InventoryOrderProduct;
import zvuv.zavakh.inventory.repositories.InventoryOrderRepository;
import zvuv.zavakh.inventory.web.dto.AddInventoryOrderProductDto;
import zvuv.zavakh.inventory.web.dto.InventoryOrderDto;
import zvuv.zavakh.inventory.web.mappers.InventoryOrderMapper;

@Service
@AllArgsConstructor
public class InventoryOrderServiceImpl implements InventoryOrderService {

    private final InventoryOrderRepository inventoryOrderRepository;
    private final InventoryOrderMapper inventoryOrderMapper;

    @Override
    public void create(InventoryOrderDto inventoryOrderDto) {
        inventoryOrderRepository.save(inventoryOrderMapper.convertDtoToEntity(inventoryOrderDto));
    }

    @Override
    public void addProduct(AddInventoryOrderProductDto addInventoryOrderProductDto) {
        InventoryOrder inventoryOrder = inventoryOrderRepository.findById(addInventoryOrderProductDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Not Found"));

        inventoryOrder.getProducts()
                .forEach(product -> {
                    if (product.getProductId().equals(addInventoryOrderProductDto.getProductId())) {
                        product.setQuantityReceived(product.getQuantityReceived() + 1);
                    }
                });

        inventoryOrderRepository.save(inventoryOrder);

        // TODO check if completed and send notification
    }

    @Override
    public boolean checkIfOrderIsCompleted(InventoryOrder inventoryOrder) {
        boolean isCompleted = true;

        for (InventoryOrderProduct product: inventoryOrder.getProducts()) {
            if (!product.getQuantityRequested().equals(product.getQuantityReceived())) {
                isCompleted = false;
                break;
            }
        }

        return isCompleted;
    }
}
