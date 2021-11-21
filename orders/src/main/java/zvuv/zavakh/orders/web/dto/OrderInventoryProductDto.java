package zvuv.zavakh.orders.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderInventoryProductDto {

    private Long productId;
    private Integer quantityRequested;
}
