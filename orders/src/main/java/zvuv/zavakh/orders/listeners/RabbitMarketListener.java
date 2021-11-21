package zvuv.zavakh.orders.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import zvuv.zavakh.orders.services.OrderService;
import zvuv.zavakh.orders.web.dto.OrderDto;

@Slf4j
@EnableRabbit
@Component
@RequiredArgsConstructor
public class RabbitMarketListener {

    private final OrderService orderService;

    @RabbitListener(queues = "${marketToOrdersQueueName}")
    public void processMessage(OrderDto orderDto) {
        log.info(String.format("Received message: %s", orderDto.toString()));
        orderService.processOrder(orderDto);
    }
}
