package zvuv.zavakh.inventory.web.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import zvuv.zavakh.inventory.domain.InventoryOrderProduct;
import zvuv.zavakh.inventory.web.dto.BlacksmithOrderProductDto;

public abstract class BlacksmithOrderProductMapperDecorator implements BlacksmithOrderProductMapper {

    @Autowired
    @Qualifier("delegate")
    private BlacksmithOrderProductMapper delegate;

    @Override
    public BlacksmithOrderProductDto convertFromEntityToDto(InventoryOrderProduct inventoryOrderProduct) {
        BlacksmithOrderProductDto blacksmithOrderProductDto = delegate.convertFromEntityToDto(inventoryOrderProduct);
        blacksmithOrderProductDto.setQuantity(
                inventoryOrderProduct.getQuantityRequested() - inventoryOrderProduct.getQuantityReceived()
        );

        return blacksmithOrderProductDto;
    }
}
