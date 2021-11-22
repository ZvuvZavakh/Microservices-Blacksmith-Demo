package zvuv.zavakh.blacksmith.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zvuv.zavakh.blacksmith.domain.BlacksmithOrder;
import zvuv.zavakh.blacksmith.domain.BlacksmithOrderProduct;
import zvuv.zavakh.blacksmith.repositories.BlacksmithOrderRepository;
import zvuv.zavakh.blacksmith.web.dto.BlacksmithOrderDto;
import zvuv.zavakh.blacksmith.web.dto.CraftedOrderProductDto;
import zvuv.zavakh.blacksmith.web.mappers.BlacksmithOrderMapper;

@Service
@RequiredArgsConstructor
public class BlacksmithOrderServiceImpl implements BlacksmithOrderService {

    private final BlacksmithOrderRepository blacksmithOrderRepository;
    private final BlacksmithOrderMapper blacksmithOrderMapper;

    @Override
    public void create(BlacksmithOrderDto blacksmithOrderDto) {
        blacksmithOrderRepository.save(blacksmithOrderMapper.convertFromDtoToEntity(blacksmithOrderDto));
    }

    @Override
    public BlacksmithOrder getOrder() {
        return blacksmithOrderRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new RuntimeException("No orders found!"));
    }

    @Override
    public void update(BlacksmithOrder blacksmithOrder) {
        blacksmithOrderRepository.save(blacksmithOrder);
    }

    @Override
    public CraftedOrderProductDto createProduct(Long blacksmithOrderId, Long productId) {
        return CraftedOrderProductDto
                .builder()
                .orderId(blacksmithOrderId)
                .productId(productId)
                .build();
    }

    @Override
    public boolean checkIfOrderIsCompleted(BlacksmithOrder blacksmithOrder) {
        return blacksmithOrder.getProducts()
                .stream()
                .allMatch(blacksmithOrderProduct -> blacksmithOrderProduct.getQuantity() == 0);
    }

    @Override
    public void delete(Long id) {
        blacksmithOrderRepository.deleteById(id);
    }
}
