package zvuv.zavakh.orders.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderInventoryDto {

    private Long orderId;
    private List<OrderInventoryProductDto> products;
}
