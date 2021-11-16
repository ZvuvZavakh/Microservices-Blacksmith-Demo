package zvuv.zavakh.market.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import zvuv.zavakh.market.web.dto.CreateOrderDto;
import zvuv.zavakh.market.web.dto.ProductDto;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService {

    private final RestTemplate restTemplate;

    @Qualifier("marketToOrdersRabbitTemplate")
    private final AmqpTemplate rabbitTemplate;

    @Value("${services.url}")
    private String productsServiceUrl;

    @Value("${marketToOrdersExchangeName}")
    private String exchange;

    @Value("${marketToOrdersRoutingKey}")
    private String routingKey;

    @Override
    public List<ProductDto> getProducts(Integer pageNumber, Integer pageSize) {
        ResponseEntity<List<ProductDto>> response = restTemplate.exchange(
                productsServiceUrl + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductDto>>() {});

        return response.getBody();
    }

    @Override
    public void createOrder(CreateOrderDto createOrderDto) {
        log.info("Sending message: " + createOrderDto.toString());
        rabbitTemplate.convertAndSend(exchange, routingKey, createOrderDto);
        log.info("Sent!");
    }
}
