package zvuv.zavakh.orders.services;

import zvuv.zavakh.orders.domain.OrderStatus;
import zvuv.zavakh.orders.web.dto.OrderDto;
import zvuv.zavakh.orders.web.dto.OrderListDto;

public interface OrderService {

    OrderDto create(OrderDto orderDto);
    OrderListDto getOrdersByCustomerId(Long id);
    void updateStatus(OrderStatus orderStatus, Long id);
    void delete(Long id);
}
