package zvuv.zavakh.blacksmith.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlacksmithOrderProductDto {

    @Null
    private Long id;

    private Long productId;
    private Integer quantity;
}
