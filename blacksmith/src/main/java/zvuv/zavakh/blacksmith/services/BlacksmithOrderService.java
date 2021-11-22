package zvuv.zavakh.blacksmith.services;

import zvuv.zavakh.blacksmith.domain.BlacksmithOrder;
import zvuv.zavakh.blacksmith.web.dto.BlacksmithOrderDto;
import zvuv.zavakh.blacksmith.web.dto.CraftedOrderProductDto;

public interface BlacksmithOrderService {

    void create(BlacksmithOrderDto blacksmithOrderDto);
    BlacksmithOrder getOrder();
    CraftedOrderProductDto createProduct(Long blacksmithOrderId, Long productId);
    void update(BlacksmithOrder blacksmithOrder);
    boolean checkIfOrderIsCompleted(BlacksmithOrder blacksmithOrder);
    void delete(Long id);
}
