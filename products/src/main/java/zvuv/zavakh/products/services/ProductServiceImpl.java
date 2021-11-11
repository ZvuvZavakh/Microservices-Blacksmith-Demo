package zvuv.zavakh.products.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zvuv.zavakh.products.domain.Product;
import zvuv.zavakh.products.repositories.ProductRepository;
import zvuv.zavakh.products.web.dto.ProductDto;
import zvuv.zavakh.products.web.mappers.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto create(ProductDto productDto) {
        Product product = productMapper.convertFromDtoToEntity(productDto);
        productRepository.save(product);

        return productMapper.convertFromEntityToDto(product);
    }

    @Override
    public List<ProductDto> list(int pageNumber, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(productMapper::convertFromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        return productMapper.convertFromEntityToDto(product);
    }

    @Override
    public ProductDto update(ProductDto productDto, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        product.setType(productDto.getType());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        productRepository.save(product);

        return productMapper.convertFromEntityToDto(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
