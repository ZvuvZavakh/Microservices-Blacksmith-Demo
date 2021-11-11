package zvuv.zavakh.blacksmith.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zvuv.zavakh.blacksmith.domain.BlacksmithOrder;
import zvuv.zavakh.blacksmith.domain.BlacksmithOrderProduct;
import zvuv.zavakh.blacksmith.repositories.BlacksmithOrderRepository;
import zvuv.zavakh.blacksmith.web.dto.BlacksmithOrderDto;
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
    public void createProduct(Long blacksmithOrderId, Long productId) {

    }

    @Override
    public boolean checkIfOrderIsCompleted(BlacksmithOrder blacksmithOrder) {
        boolean isCompleted = true;

        for (BlacksmithOrderProduct blacksmithOrderProduct: blacksmithOrder.getProducts()) {
            if (blacksmithOrderProduct.getQuantity() != 0) {
                isCompleted = false;
                break;
            }
        }

        return isCompleted;
    }

    @Override
    public void delete(Long id) {
        blacksmithOrderRepository.deleteById(id);
    }
}
