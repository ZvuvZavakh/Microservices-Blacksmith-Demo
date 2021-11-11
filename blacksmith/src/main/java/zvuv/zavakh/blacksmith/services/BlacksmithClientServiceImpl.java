package zvuv.zavakh.blacksmith.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import zvuv.zavakh.blacksmith.web.dto.CraftedProductDto;

@Service
@RequiredArgsConstructor
public class BlacksmithClientServiceImpl implements BlacksmithClientService {

    private final RestTemplate restTemplate;

    @Override
    public void sendProduct(CraftedProductDto craftedProductDto) {

    }
}
