package zvuv.zavakh.orders.web.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zvuv.zavakh.orders.services.OrderService;
import zvuv.zavakh.orders.web.dto.OrderDto;
import zvuv.zavakh.orders.web.dto.OrderListDto;
import zvuv.zavakh.orders.web.dto.OrderUpdateDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<OrderListDto> getOrdersByCustomerId(@PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
    }

    @PostMapping("/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@RequestBody @Valid OrderUpdateDto orderUpdateDto,
                             @PathVariable("id") Long id) {

        orderService.updateStatus(orderUpdateDto.getOrderStatus(), id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);
    }
}
