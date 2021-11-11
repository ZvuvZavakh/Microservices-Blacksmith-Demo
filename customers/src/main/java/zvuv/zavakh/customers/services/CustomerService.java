package zvuv.zavakh.customers.services;

import zvuv.zavakh.customers.web.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto create(CustomerDto customerDto);
    List<CustomerDto> list(int pageNumber, int pageSize);
    CustomerDto getById(Long id);
    CustomerDto update(CustomerDto customerDto, Long id);
    void delete(Long id);
}
