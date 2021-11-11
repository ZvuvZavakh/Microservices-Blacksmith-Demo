package zvuv.zavakh.inventory.web.mappers;

import org.mapstruct.Mapper;
import zvuv.zavakh.inventory.domain.InventoryOrderProduct;
import zvuv.zavakh.inventory.web.dto.InventoryOrderProductDto;

@Mapper(componentModel = "spring")
public interface InventoryOrderProductMapper {

    InventoryOrderProductDto convertEntityToDto(InventoryOrderProduct inventoryOrderProduct);
    InventoryOrderProduct convertDtoToEntity(InventoryOrderProductDto inventoryOrderProductDto);
}
