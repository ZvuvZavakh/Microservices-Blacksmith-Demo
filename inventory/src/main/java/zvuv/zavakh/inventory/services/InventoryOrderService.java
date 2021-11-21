package zvuv.zavakh.inventory.services;

import zvuv.zavakh.inventory.domain.InventoryOrder;
import zvuv.zavakh.inventory.web.dto.AddInventoryOrderProductDto;
import zvuv.zavakh.inventory.web.dto.InventoryOrderDto;

public interface InventoryOrderService {

    InventoryOrder create(InventoryOrderDto inventoryOrderDto);
    InventoryOrder populateFromStoredProducts(InventoryOrder inventoryOrder);
    void processOrder(InventoryOrderDto inventoryOrderDto);
    void addProduct(AddInventoryOrderProductDto addInventoryOrderProductDto);
    boolean checkIfOrderIsCompleted(InventoryOrder inventoryOrder);
}
