package zvuv.zavakh.orders.web.dto;

import lombok.*;
import zvuv.zavakh.orders.domain.OrderStatus;

import javax.validation.constraints.Null;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderDto {

    @Null
    private Long id;

    private Long customerId;
    private List<OrderProductDto> orderProducts;

    @Null
    private OrderStatus orderStatus;
}
