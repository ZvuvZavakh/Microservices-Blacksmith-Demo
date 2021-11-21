package zvuv.zavakh.inventory.web.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import zvuv.zavakh.inventory.domain.InventoryOrderProduct;
import zvuv.zavakh.inventory.web.dto.BlacksmithOrderProductDto;

@Mapper(componentModel = "spring")
@DecoratedWith(BlacksmithOrderProductMapperDecorator.class)
public interface BlacksmithOrderProductMapper {

    BlacksmithOrderProductMapper INSTANCE = Mappers.getMapper(BlacksmithOrderProductMapper.class);

    BlacksmithOrderProductDto convertFromEntityToDto(InventoryOrderProduct inventoryOrderProduct);
}
