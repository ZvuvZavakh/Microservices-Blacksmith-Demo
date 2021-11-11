package zvuv.zavakh.customers.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@Builder
public class CustomerDto {

    @Null
    private Long id;

    private String name;
    private Integer deposit;
}
