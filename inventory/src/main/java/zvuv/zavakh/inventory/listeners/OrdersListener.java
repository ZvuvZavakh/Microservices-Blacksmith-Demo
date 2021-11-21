package zvuv.zavakh.inventory.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import zvuv.zavakh.inventory.services.InventoryOrderService;
import zvuv.zavakh.inventory.web.dto.InventoryOrderDto;

@Slf4j
@EnableRabbit
@Component
@RequiredArgsConstructor
public class OrdersListener {

    private final InventoryOrderService inventoryOrderService;

    @RabbitListener(queues = "${ordersToInventoryQueueName}")
    public void processOrder(InventoryOrderDto inventoryOrderDto) {
        log.info("Received message: " + inventoryOrderDto.toString());
        inventoryOrderService.processOrder(inventoryOrderDto);
    }
}
