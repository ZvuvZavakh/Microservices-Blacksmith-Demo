package zvuv.zavakh.market.web.dto;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {

    private Long customerId;
    private List<CreateProductDto> orderProducts;
}
