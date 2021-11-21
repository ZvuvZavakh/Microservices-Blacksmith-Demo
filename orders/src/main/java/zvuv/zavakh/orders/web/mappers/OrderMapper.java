package zvuv.zavakh.orders.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import zvuv.zavakh.orders.domain.Order;
import zvuv.zavakh.orders.web.dto.OrderDto;
import zvuv.zavakh.orders.web.dto.OrderInventoryDto;

@Mapper(componentModel = "spring", uses = { OrderProductMapper.class, OrderInventoryProductMapper.class })
public interface OrderMapper {

    OrderDto convertFromEntityToDto(Order order);
    Order convertFromDtoToEntity(OrderDto orderDto);

    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "orderProducts", target = "products")
    OrderInventoryDto convertFromEntityToInventoryDto(Order order);
}
