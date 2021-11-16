package zvuv.zavakh.market.web.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zvuv.zavakh.market.services.MarketService;
import zvuv.zavakh.market.web.dto.CreateOrderDto;
import zvuv.zavakh.market.web.dto.ProductDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/market")
@AllArgsConstructor
public class MarketController {

    private final MarketService marketService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") Integer pageSize) {

        return ResponseEntity.ok(marketService.getProducts(pageNumber, pageSize));
    }

    @PostMapping("/orders/create")
    public void createOrder(@RequestBody @Valid CreateOrderDto createOrderDto) {
        marketService.createOrder(createOrderDto);
    }
}
