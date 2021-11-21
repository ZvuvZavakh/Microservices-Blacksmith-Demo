package zvuv.zavakh.inventory.services;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zvuv.zavakh.inventory.domain.InventoryOrder;
import zvuv.zavakh.inventory.domain.InventoryOrderProduct;
import zvuv.zavakh.inventory.domain.InventoryProduct;
import zvuv.zavakh.inventory.repositories.InventoryOrderRepository;
import zvuv.zavakh.inventory.web.dto.AddInventoryOrderProductDto;
import zvuv.zavakh.inventory.web.dto.InventoryOrderDto;
import zvuv.zavakh.inventory.web.mappers.InventoryOrderMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryOrderServiceImpl implements InventoryOrderService {

    private final InventoryOrderRepository inventoryOrderRepository;
    private final InventoryOrderMapper inventoryOrderMapper;
    private final InventoryProductService inventoryProductService;

    @Override
    public InventoryOrder create(InventoryOrderDto inventoryOrderDto) {
        return inventoryOrderRepository.save(inventoryOrderMapper.convertDtoToEntity(inventoryOrderDto));
    }

    @Override
    public void processOrder(InventoryOrderDto inventoryOrderDto) {
        InventoryOrder inventoryOrder = populateFromStoredProducts(create(inventoryOrderDto));

        if (checkIfOrderIsCompleted(inventoryOrder)) {
            // TODO SEND ORDER COMPLETED REQUEST
        } else {
            // TODO SEND BLACKSMITH REQUEST
        }
    }

    @Override
    public InventoryOrder populateFromStoredProducts(InventoryOrder inventoryOrder) {
        List<Long> productIds = inventoryOrder.getProducts()
                .stream()
                .map(InventoryOrderProduct::getProductId)
                .collect(Collectors.toList());

        List<InventoryProduct> storedProducts = inventoryProductService.findByProductIds(productIds);
        boolean populated = false;

        for (InventoryOrderProduct orderProduct : inventoryOrder.getProducts()) {
            Long productId = orderProduct.getProductId();
            Integer quantityRequested = orderProduct.getQuantityRequested();
            InventoryProduct inventoryProduct = storedProducts.stream()
                    .filter(product -> product.getProductId().equals(productId))
                    .findAny()
                    .orElse(null);

            if (inventoryProduct != null) {
                Integer amount = inventoryProduct.getAmount();

                if (amount > 0) {
                    int newAmount = 0;
                    int quantityReceived = 0;

                    if (amount >= quantityRequested) {
                        newAmount = amount - quantityRequested;
                        quantityReceived = quantityRequested;
                    } else {
                        quantityReceived = quantityRequested - amount;
                    }

                    populated = true;
                    inventoryProduct.setAmount(newAmount);
                    inventoryProductService.update(inventoryProduct);
                    orderProduct.setQuantityReceived(quantityReceived);
                }
            }
        }

        if (populated) {
            inventoryOrderRepository.save(inventoryOrder);
        }

        return inventoryOrder;
    }

    @Override
    public boolean checkIfOrderIsCompleted(InventoryOrder inventoryOrder) {
        return inventoryOrder.getProducts()
                .stream()
                .allMatch(product -> product.getQuantityRequested().equals(product.getQuantityReceived()));
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

        if (checkIfOrderIsCompleted(inventoryOrder)) {
            // TODO SEND ORDER COMPLETED REQUEST
        }
    }
}
