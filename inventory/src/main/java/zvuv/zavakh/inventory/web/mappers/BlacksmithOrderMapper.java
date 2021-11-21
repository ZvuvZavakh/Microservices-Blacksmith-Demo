package zvuv.zavakh.inventory.web.mappers;

import org.mapstruct.Mapper;
import zvuv.zavakh.inventory.domain.InventoryOrder;
import zvuv.zavakh.inventory.web.dto.BlacksmithOrderDto;

@Mapper(componentModel = "spring", uses = BlacksmithOrderProductMapper.class)
public interface BlacksmithOrderMapper {

    BlacksmithOrderDto convertFromEntityToDto(InventoryOrder inventoryOrder);
}
