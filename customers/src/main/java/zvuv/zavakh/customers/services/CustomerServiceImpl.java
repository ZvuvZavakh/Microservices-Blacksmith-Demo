package zvuv.zavakh.customers.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zvuv.zavakh.customers.domain.Customer;
import zvuv.zavakh.customers.repositories.CustomerRepository;
import zvuv.zavakh.customers.web.dto.CustomerDto;
import zvuv.zavakh.customers.web.mappers.CustomerMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        Customer customer = customerMapper.convertFromDtoToEntity(customerDto);
        customerRepository.save(customer);
        return customerMapper.convertFromEntityToDto(customer);
    }

    @Override
    public List<CustomerDto> list(int pageNumber, int pageSize) {
        return customerRepository.findAll(PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(customerMapper::convertFromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));

        return customerMapper.convertFromEntityToDto(customer);
    }

    @Override
    public CustomerDto update(CustomerDto customerDto, Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));

        customer.setName(customerDto.getName());
        customer.setDeposit(customerDto.getDeposit());
        customerRepository.save(customer);

        return customerMapper.convertFromEntityToDto(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
