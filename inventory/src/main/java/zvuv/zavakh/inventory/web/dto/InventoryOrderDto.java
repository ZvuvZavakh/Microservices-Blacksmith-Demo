package zvuv.zavakh.inventory.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryOrderDto {

    private Long orderId;
    private List<InventoryOrderProductDto> products;
}
