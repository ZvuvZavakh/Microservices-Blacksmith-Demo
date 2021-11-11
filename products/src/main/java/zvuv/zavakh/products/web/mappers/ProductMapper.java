package zvuv.zavakh.products.web.mappers;

import org.mapstruct.Mapper;
import zvuv.zavakh.products.domain.Product;
import zvuv.zavakh.products.web.dto.ProductDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto convertFromEntityToDto(Product product);
    Product convertFromDtoToEntity(ProductDto productDto);
}
