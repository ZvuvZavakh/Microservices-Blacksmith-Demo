package zvuv.zavakh.orders.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zvuv.zavakh.orders.domain.OrderStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateDto {

    private OrderStatus orderStatus;
}
