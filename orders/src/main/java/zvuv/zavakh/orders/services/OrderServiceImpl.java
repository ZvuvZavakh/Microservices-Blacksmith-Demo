package zvuv.zavakh.orders.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zvuv.zavakh.orders.domain.Order;
import zvuv.zavakh.orders.domain.OrderProduct;
import zvuv.zavakh.orders.domain.OrderStatus;
import zvuv.zavakh.orders.repositories.OrderRepository;
import zvuv.zavakh.orders.web.dto.OrderDto;
import zvuv.zavakh.orders.web.dto.OrderListDto;
import zvuv.zavakh.orders.web.dto.OrderProductDto;
import zvuv.zavakh.orders.web.mappers.OrderMapper;
import zvuv.zavakh.orders.web.mappers.OrderProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;
    private final OrderRepository orderRepository;

    @Override
    public OrderDto create(OrderDto orderDto) {
        Order order = orderMapper.convertFromDtoToEntity(orderDto);
        orderRepository.save(order);

        return orderMapper.convertFromEntityToDto(order);
    }

    @Override
    public OrderListDto getOrdersByCustomerId(Long id) {
        List<Order> orders = orderRepository.getAllByCustomerId(id);
        List<OrderDto> orderDtoList = orders.stream()
                .map(orderMapper::convertFromEntityToDto)
                .collect(Collectors.toList());

        return OrderListDto
                .builder()
                .orders(orderDtoList)
                .build();
    }

    @Override
    public void updateStatus(OrderStatus orderStatus, Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
