package zvuv.zavakh.products.services;

import zvuv.zavakh.products.web.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto create(ProductDto productDto);
    List<ProductDto> list(int pageNumber, int pageSize);
    ProductDto getById(Long id);
    ProductDto update(ProductDto productDto, Long id);
    void delete(Long id);
}
