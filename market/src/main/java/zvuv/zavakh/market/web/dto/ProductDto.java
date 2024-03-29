package zvuv.zavakh.market.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String description;
    private Integer price;
}
