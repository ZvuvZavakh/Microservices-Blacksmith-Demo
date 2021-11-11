package zvuv.zavakh.market.web.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zvuv.zavakh.market.services.MarketService;
import zvuv.zavakh.market.web.dto.ProductDto;

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
}
