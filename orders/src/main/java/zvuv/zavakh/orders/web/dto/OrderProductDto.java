package zvuv.zavakh.orders.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductDto {

    @Null
    private Long id;

    private Long productId;
    private Integer quantity;
}
