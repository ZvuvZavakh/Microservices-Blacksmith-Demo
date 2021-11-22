package zvuv.zavakh.blacksmith.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CraftedOrderProductDto {

    private Long orderId;
    private Long productId;
}
