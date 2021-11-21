package zvuv.zavakh.orders.services;

import zvuv.zavakh.orders.domain.Order;
import zvuv.zavakh.orders.domain.OrderStatus;
import zvuv.zavakh.orders.web.dto.OrderDto;
import zvuv.zavakh.orders.web.dto.OrderInventoryDto;
import zvuv.zavakh.orders.web.dto.OrderListDto;

public interface OrderService {

    Order create(OrderDto orderDto);
    void sendInventoryEvent(OrderInventoryDto orderInventoryDto);
    OrderListDto getOrdersByCustomerId(Long id);
    void updateStatus(OrderStatus orderStatus, Long id);
    void delete(Long id);
    void processOrder(OrderDto orderDto);
}
