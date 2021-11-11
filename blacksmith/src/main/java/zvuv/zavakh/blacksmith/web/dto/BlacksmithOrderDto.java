package zvuv.zavakh.blacksmith.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlacksmithOrderDto {

    @Null
    private Long id;

    private Long orderId;
    private List<BlacksmithOrderProductDto> products;
}
