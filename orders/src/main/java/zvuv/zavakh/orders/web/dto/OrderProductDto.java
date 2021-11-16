package zvuv.zavakh.orders.web.dto;

import lombok.*;

import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderProductDto {

    @Null
    private Long id;

    private Long productId;
    private Integer quantity;
}
