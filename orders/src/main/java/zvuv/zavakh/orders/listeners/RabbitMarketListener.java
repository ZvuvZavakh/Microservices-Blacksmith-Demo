package zvuv.zavakh.orders.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;
import zvuv.zavakh.orders.web.dto.OrderDto;

@Slf4j
@EnableRabbit
@Component
public class RabbitMarketListener {

    @RabbitListener(queues = "${marketToOrdersQueueName}")
    public void processMessage(OrderDto orderDto) {
        log.info(String.format("Received message: %s", orderDto.toString()));
    }
}
