package zvuv.zavakh.market.services;

import zvuv.zavakh.market.web.dto.ProductDto;

import java.util.List;

public interface MarketService {

    List<ProductDto> getProducts(Integer pageNumber, Integer pageSize);
}
