package zvuv.zavakh.inventory.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BlacksmithOrderProductDto {

    private Long productId;
    private Integer quantity;
}
