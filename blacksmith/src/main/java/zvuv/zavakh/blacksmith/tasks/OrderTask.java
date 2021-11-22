package zvuv.zavakh.blacksmith.tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zvuv.zavakh.blacksmith.domain.BlacksmithOrder;
import zvuv.zavakh.blacksmith.domain.BlacksmithOrderProduct;
import zvuv.zavakh.blacksmith.services.BlacksmithOrderService;
import zvuv.zavakh.blacksmith.web.dto.CraftedOrderProductDto;

@Component
@RequiredArgsConstructor
public class OrderTask {

    private final BlacksmithOrderService blacksmithOrderService;

    public void processOrders() {
        BlacksmithOrder blacksmithOrder = blacksmithOrderService.getOrder();
        BlacksmithOrderProduct blacksmithOrderProduct = blacksmithOrder.getProducts()
                .stream()
                .filter(product -> product.getQuantity() > 0)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No products needed!"));

        CraftedOrderProductDto craftedProduct = blacksmithOrderService.createProduct(
                blacksmithOrderProduct.getId(), blacksmithOrderProduct.getProductId()
        );

        // TODO send product
        blacksmithOrderProduct.setQuantity(blacksmithOrderProduct.getQuantity() - 1);

        if (blacksmithOrderService.checkIfOrderIsCompleted(blacksmithOrder)) {
            blacksmithOrderService.delete(blacksmithOrder.getId());
        } else {
            blacksmithOrderService.update(blacksmithOrder);
        }
    }
}
