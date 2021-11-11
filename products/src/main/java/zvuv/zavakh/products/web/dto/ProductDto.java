package zvuv.zavakh.products.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zvuv.zavakh.products.domain.ProductType;

import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    @Null
    private Long id;

    private ProductType type;
    private String description;
    private Integer price;
}
