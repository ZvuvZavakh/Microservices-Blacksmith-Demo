package zvuv.zavakh.blacksmith.services;

import zvuv.zavakh.blacksmith.web.dto.CraftedProductDto;

public interface BlacksmithClientService {

    void sendProduct(CraftedProductDto craftedProductDto);
}
