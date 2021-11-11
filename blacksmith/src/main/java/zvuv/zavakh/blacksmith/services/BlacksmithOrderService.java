package zvuv.zavakh.blacksmith.services;

import zvuv.zavakh.blacksmith.domain.BlacksmithOrder;
import zvuv.zavakh.blacksmith.web.dto.BlacksmithOrderDto;

public interface BlacksmithOrderService {

    void create(BlacksmithOrderDto blacksmithOrderDto);
    void createProduct(Long blacksmithOrderId, Long productId);
    boolean checkIfOrderIsCompleted(BlacksmithOrder blacksmithOrder);
    void delete(Long id);
}
