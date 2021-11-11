package zvuv.zavakh.market.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import zvuv.zavakh.market.web.dto.ProductDto;

import java.util.List;

@Service
public class MarketServiceImpl implements MarketService {

    private final RestTemplate restTemplate;

    @Value("${services.url}")
    private String productsServiceUrl;

    @Autowired
    public MarketServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ProductDto> getProducts(Integer pageNumber, Integer pageSize) {
        ResponseEntity<List<ProductDto>> response = restTemplate.exchange(
                productsServiceUrl + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductDto>>() {});

        return response.getBody();
    }
}
