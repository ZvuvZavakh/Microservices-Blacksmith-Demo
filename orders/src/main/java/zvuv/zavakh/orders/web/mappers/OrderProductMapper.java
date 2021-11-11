package zvuv.zavakh.orders.web.mappers;

import org.mapstruct.Mapper;
import zvuv.zavakh.orders.domain.OrderProduct;
import zvuv.zavakh.orders.web.dto.OrderProductDto;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    OrderProductDto convertFromEntityToDto(OrderProduct orderProduct);
    OrderProduct convertFromDtoToEntity(OrderProductDto orderProductDto);
}
