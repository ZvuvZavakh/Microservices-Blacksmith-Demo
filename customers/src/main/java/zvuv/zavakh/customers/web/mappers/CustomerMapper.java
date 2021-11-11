package zvuv.zavakh.customers.web.mappers;

import org.mapstruct.Mapper;
import zvuv.zavakh.customers.domain.Customer;
import zvuv.zavakh.customers.web.dto.CustomerDto;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto convertFromEntityToDto(Customer customer);
    Customer convertFromDtoToEntity(CustomerDto customerDto);
}
