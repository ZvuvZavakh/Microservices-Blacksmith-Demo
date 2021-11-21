package zvuv.zavakh.orders.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import zvuv.zavakh.orders.domain.Order;
import zvuv.zavakh.orders.domain.OrderProduct;
import zvuv.zavakh.orders.domain.OrderStatus;
import zvuv.zavakh.orders.repositories.OrderRepository;
import zvuv.zavakh.orders.web.dto.OrderDto;
import zvuv.zavakh.orders.web.dto.OrderInventoryDto;
import zvuv.zavakh.orders.web.dto.OrderListDto;
import zvuv.zavakh.orders.web.dto.OrderProductDto;
import zvuv.zavakh.orders.web.mappers.OrderMapper;
import zvuv.zavakh.orders.web.mappers.OrderProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;
    private final OrderRepository orderRepository;

    @Qualifier("ordersRabbitTemplate")
    private final AmqpTemplate rabbitTemplate;

    @Value("${ordersToInventoryExchangeName}")
    private String exchange;

    @Value("${ordersToInventoryRoutingKey}")
    private String routingKey;

    @Override
    public void processOrder(OrderDto orderDto) {
        Order order = create(orderDto);
        sendInventoryEvent(orderMapper.convertFromEntityToInventoryDto(order));
    }

    @Override
    public Order create(OrderDto orderDto) {
        Order order = orderMapper.convertFromDtoToEntity(orderDto);

        order.setOrderStatus(OrderStatus.PENDING);
        orderRepository.save(order);

        return order;
    }

    @Override
    public void sendInventoryEvent(OrderInventoryDto orderInventoryDto) {
        log.info("Sending message to inventory: " + orderInventoryDto.toString());
        rabbitTemplate.convertAndSend(exchange, routingKey, orderInventoryDto);
        log.info("Sent!");
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
