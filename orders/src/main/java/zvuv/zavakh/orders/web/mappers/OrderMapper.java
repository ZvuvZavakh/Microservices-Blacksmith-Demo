package zvuv.zavakh.orders.web.mappers;

import org.mapstruct.Mapper;
import zvuv.zavakh.orders.domain.Order;
import zvuv.zavakh.orders.web.dto.OrderDto;

@Mapper(componentModel = "spring", uses = OrderProductMapper.class)
public interface OrderMapper {

    OrderDto convertFromEntityToDto(Order order);
    Order convertFromDtoToEntity(OrderDto orderDto);
}
