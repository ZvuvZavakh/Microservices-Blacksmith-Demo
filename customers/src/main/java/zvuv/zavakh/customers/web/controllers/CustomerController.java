package zvuv.zavakh.customers.web.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zvuv.zavakh.customers.services.CustomerService;
import zvuv.zavakh.customers.web.dto.CustomerDto;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody @Valid CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.create(customerDto));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> list(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize) {

        return ResponseEntity.ok(customerService.list(pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@RequestBody @Valid CustomerDto customerDto,
                                              @PathVariable("id") Long id) {

        return ResponseEntity.ok(customerService.update(customerDto, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        customerService.delete(id);
    }
}
