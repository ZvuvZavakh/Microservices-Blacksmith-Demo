package zvuv.zavakh.inventory.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class BlacksmithOrderDto {

    private Long orderId;
    private List<BlacksmithOrderProductDto> products;
}
