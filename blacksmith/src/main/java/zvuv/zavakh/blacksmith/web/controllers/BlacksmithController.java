package zvuv.zavakh.blacksmith.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zvuv.zavakh.blacksmith.services.BlacksmithOrderService;
import zvuv.zavakh.blacksmith.web.dto.BlacksmithOrderDto;

@RestController
@RequestMapping("/api/v1/blacksmith")
@RequiredArgsConstructor
public class BlacksmithController {

    private final BlacksmithOrderService blacksmithOrderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(BlacksmithOrderDto blacksmithOrderDto) {
        blacksmithOrderService.create(blacksmithOrderDto);
    }
}
