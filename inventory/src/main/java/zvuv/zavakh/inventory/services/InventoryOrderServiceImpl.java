package zvuv.zavakh.inventory.services;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import zvuv.zavakh.inventory.domain.InventoryOrder;
import zvuv.zavakh.inventory.domain.InventoryOrderProduct;
import zvuv.zavakh.inventory.domain.InventoryProduct;
import zvuv.zavakh.inventory.repositories.InventoryOrderRepository;
import zvuv.zavakh.inventory.web.dto.AddInventoryOrderProductDto;
import zvuv.zavakh.inventory.web.dto.BlacksmithOrderDto;
import zvuv.zavakh.inventory.web.dto.CompletedOrderDto;
import zvuv.zavakh.inventory.web.dto.InventoryOrderDto;
import zvuv.zavakh.inventory.web.mappers.BlacksmithOrderMapper;
import zvuv.zavakh.inventory.web.mappers.InventoryOrderMapper;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryOrderServiceImpl implements InventoryOrderService {

    @Value("${ordersExchangeName}")
    private String ordersExchange;

    @Value("${completedOrdersRoutingKey}")
    private String completedOrdersRoutingKey;

    @Value("${blacksmithExchangeName}")
    private String blacksmithExchange;

    @Value("${inventoryToBlacksmithRoutingKey}")
    private String blacksmithRoutingKey;

    private final InventoryOrderRepository inventoryOrderRepository;
    private final InventoryOrderMapper inventoryOrderMapper;
    private final InventoryProductService inventoryProductService;
    private final BlacksmithOrderMapper blacksmithOrderMapper;

    @Qualifier("inventoryRabbitTemplate")
    private final AmqpTemplate rabbitTemplate;

    @Override
    public InventoryOrder create(InventoryOrderDto inventoryOrderDto) {
        return inventoryOrderRepository.save(inventoryOrderMapper.convertDtoToEntity(inventoryOrderDto));
    }

    @Override
    public void processOrder(InventoryOrderDto inventoryOrderDto) {
        InventoryOrder inventoryOrder = populateFromStoredProducts(create(inventoryOrderDto));

        if (checkIfOrderIsCompleted(inventoryOrder)) {
            sendOrderCompletedNotification(inventoryOrder);
        } else {
            sendBlacksmithOrder(inventoryOrder);
        }
    }

    @Override
    public void sendOrderCompletedNotification(InventoryOrder inventoryOrder) {
        CompletedOrderDto completedOrderDto = CompletedOrderDto
                .builder()
                .orderId(inventoryOrder.getOrderId())
                .build();

        log.info("Sending message: " + completedOrderDto.toString());
        rabbitTemplate.convertAndSend(ordersExchange, completedOrdersRoutingKey, completedOrderDto);
        log.info("Sent!");
    }

    @Override
    public void sendBlacksmithOrder(InventoryOrder inventoryOrder) {
        BlacksmithOrderDto blacksmithOrderDto = blacksmithOrderMapper.convertFromEntityToDto(inventoryOrder);

        log.info("Sending message: " + blacksmithOrderDto.toString());
        rabbitTemplate.convertAndSend(blacksmithExchange, blacksmithRoutingKey, blacksmithOrderDto);
        log.info("Sent!");
    }

    @Override
    public InventoryOrder populateFromStoredProducts(InventoryOrder inventoryOrder) {
        List<Long> productIds = inventoryOrder.getProducts()
                .stream()
                .map(InventoryOrderProduct::getProductId)
                .collect(Collectors.toList());

        List<InventoryProduct> storedProducts = inventoryProductService.findByProductIds(productIds);
        boolean populated = false;

        for (InventoryOrderProduct orderProduct : inventoryOrder.getProducts()) {
            Long productId = orderProduct.getProductId();
            Integer quantityRequested = orderProduct.getQuantityRequested();
            InventoryProduct inventoryProduct = storedProducts.stream()
                    .filter(product -> product.getProductId().equals(productId))
                    .findAny()
                    .orElse(null);

            if (inventoryProduct != null) {
                Integer amount = inventoryProduct.getAmount();

                if (amount > 0) {
                    int newAmount = 0;
                    int quantityReceived = 0;

                    if (amount >= quantityRequested) {
                        newAmount = amount - quantityRequested;
                        quantityReceived = quantityRequested;
                    } else {
                        quantityReceived = quantityRequested - amount;
                    }

                    populated = true;
                    inventoryProduct.setAmount(newAmount);
                    inventoryProductService.update(inventoryProduct);
                    orderProduct.setQuantityReceived(quantityReceived);
                }
            }
        }

        if (populated) {
            inventoryOrderRepository.save(inventoryOrder);
        }

        return inventoryOrder;
    }

    @Override
    public boolean checkIfOrderIsCompleted(InventoryOrder inventoryOrder) {
        return inventoryOrder.getProducts()
                .stream()
                .allMatch(product -> product.getQuantityRequested().equals(product.getQuantityReceived()));
    }

    @Override
    public void addProduct(AddInventoryOrderProductDto addInventoryOrderProductDto) {
        InventoryOrder inventoryOrder = inventoryOrderRepository.findById(addInventoryOrderProductDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Not Found"));

        inventoryOrder.getProducts()
                .forEach(product -> {
                    if (product.getProductId().equals(addInventoryOrderProductDto.getProductId())) {
                        product.setQuantityReceived(product.getQuantityReceived() + 1);
                    }
                });

        inventoryOrderRepository.save(inventoryOrder);

        if (checkIfOrderIsCompleted(inventoryOrder)) {
            // TODO SEND ORDER COMPLETED REQUEST
        }
    }
}
