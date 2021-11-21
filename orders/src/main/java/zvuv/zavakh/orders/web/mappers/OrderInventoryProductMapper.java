package zvuv.zavakh.orders.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import zvuv.zavakh.orders.domain.OrderProduct;
import zvuv.zavakh.orders.web.dto.OrderInventoryProductDto;

@Mapper(componentModel = "spring")
public interface OrderInventoryProductMapper {

    @Mapping(source = "quantity", target = "quantityRequested")
    OrderInventoryProductDto convertFromEntityToInventoryDto(OrderProduct orderProduct);
}
