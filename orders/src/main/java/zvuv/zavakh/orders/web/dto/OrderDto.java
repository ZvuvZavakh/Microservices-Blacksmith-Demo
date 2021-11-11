package zvuv.zavakh.orders.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zvuv.zavakh.orders.domain.OrderStatus;

import javax.validation.constraints.Null;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    @Null
    private Long id;

    private Long customerId;
    private List<OrderProductDto> orderProducts;
    private OrderStatus orderStatus;
}
