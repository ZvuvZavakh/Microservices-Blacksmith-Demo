package zvuv.zavakh.inventory.web.mappers;

import org.mapstruct.Mapper;
import zvuv.zavakh.inventory.domain.InventoryOrder;
import zvuv.zavakh.inventory.web.dto.InventoryOrderDto;

@Mapper(componentModel = "spring", uses = InventoryOrderProductMapper.class)
public interface InventoryOrderMapper {

    InventoryOrderDto convertEntityToDto(InventoryOrder inventoryOrder);
    InventoryOrder convertDtoToEntity(InventoryOrderDto inventoryOrderDto);
}
