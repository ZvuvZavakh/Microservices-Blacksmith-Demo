package zvuv.zavakh.inventory.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zvuv.zavakh.inventory.domain.InventoryOrder;
import zvuv.zavakh.inventory.domain.InventoryOrderProduct;
import zvuv.zavakh.inventory.domain.InventoryProduct;
import zvuv.zavakh.inventory.repositories.InventoryOrderRepository;
import zvuv.zavakh.inventory.web.mappers.InventoryOrderMapper;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryOrderServiceTest {

    @Mock
    private InventoryOrderRepository inventoryOrderRepository;

    @Mock
    private InventoryOrderMapper inventoryOrderMapper;

    @Mock
    private InventoryProductService inventoryProductService;

    @InjectMocks
    private InventoryOrderServiceImpl inventoryOrderService;

    @Test
    void populateProductsFromInventoryAmountEqualsRequested() {
        Long product1Id = 1L;
        Long product2Id = 2L;
        Long product3Id = 3L;

        int product1Requested = 100;
        int product2Requested = 200;
        int product3Requested = 300;

        int product1Amount = 100;
        int product2Amount = 200;
        int product3Amount = 300;

        InventoryOrder inventoryOrder = InventoryOrder
                .builder()
                .products(List.of(
                        InventoryOrderProduct
                                .builder()
                                .productId(product1Id)
                                .quantityRequested(product1Requested)
                                .quantityReceived(0)
                                .build(),

                        InventoryOrderProduct
                                .builder()
                                .productId(product2Id)
                                .quantityRequested(product2Requested)
                                .quantityReceived(0)
                                .build(),

                        InventoryOrderProduct
                                .builder()
                                .productId(product3Id)
                                .quantityRequested(product3Requested)
                                .quantityReceived(0)
                                .build()
                ))
                .build();

        List<InventoryProduct> inventoryProducts = List.of(
                InventoryProduct
                        .builder()
                        .productId(product1Id)
                        .amount(product1Amount)
                        .build(),

                InventoryProduct
                        .builder()
                        .productId(product2Id)
                        .amount(product2Amount)
                        .build(),

                InventoryProduct
                        .builder()
                        .productId(product3Id)
                        .amount(product3Amount)
                        .build()
        );

        when(inventoryProductService.findByProductIds(any())).thenReturn(inventoryProducts);

        inventoryOrderService.populateFromStoredProducts(inventoryOrder);

        verify(inventoryProductService, times(3)).update(any());

        assertThat(inventoryOrder.getProducts().get(0).getQuantityReceived()).isEqualTo(product1Requested);
        assertThat(inventoryOrder.getProducts().get(1).getQuantityReceived()).isEqualTo(product2Requested);
        assertThat(inventoryOrder.getProducts().get(2).getQuantityReceived()).isEqualTo(product3Requested);

        assertThat(inventoryProducts.get(0).getAmount()).isEqualTo(0);
        assertThat(inventoryProducts.get(1).getAmount()).isEqualTo(0);
        assertThat(inventoryProducts.get(2).getAmount()).isEqualTo(0);
    }

    @Test
    void populateProductsFromInventoryAmountOverRequested() {
        Long product1Id = 1L;
        Long product2Id = 2L;
        Long product3Id = 3L;

        int product1Requested = 100;
        int product2Requested = 200;
        int product3Requested = 300;

        int product1Amount = 400;
        int product2Amount = 500;
        int product3Amount = 600;

        InventoryOrder inventoryOrder = InventoryOrder
                .builder()
                .products(List.of(
                        InventoryOrderProduct
                                .builder()
                                .productId(product1Id)
                                .quantityRequested(product1Requested)
                                .quantityReceived(0)
                                .build(),

                        InventoryOrderProduct
                                .builder()
                                .productId(product2Id)
                                .quantityRequested(product2Requested)
                                .quantityReceived(0)
                                .build(),

                        InventoryOrderProduct
                                .builder()
                                .productId(product3Id)
                                .quantityRequested(product3Requested)
                                .quantityReceived(0)
                                .build()
                ))
                .build();

        List<InventoryProduct> inventoryProducts = List.of(
                InventoryProduct
                        .builder()
                        .productId(product1Id)
                        .amount(product1Amount)
                        .build(),

                InventoryProduct
                        .builder()
                        .productId(product2Id)
                        .amount(product2Amount)
                        .build(),

                InventoryProduct
                        .builder()
                        .productId(product3Id)
                        .amount(product3Amount)
                        .build()
        );

        when(inventoryProductService.findByProductIds(any())).thenReturn(inventoryProducts);

        inventoryOrderService.populateFromStoredProducts(inventoryOrder);

        verify(inventoryProductService, times(3)).update(any());

        assertThat(inventoryOrder.getProducts().get(0).getQuantityReceived()).isEqualTo(product1Requested);
        assertThat(inventoryOrder.getProducts().get(1).getQuantityReceived()).isEqualTo(product2Requested);
        assertThat(inventoryOrder.getProducts().get(2).getQuantityReceived()).isEqualTo(product3Requested);

        assertThat(inventoryProducts.get(0).getAmount()).isEqualTo(product1Amount - product1Requested);
        assertThat(inventoryProducts.get(1).getAmount()).isEqualTo(product2Amount - product2Requested);
        assertThat(inventoryProducts.get(2).getAmount()).isEqualTo(product3Amount - product3Requested);
    }

    @Test
    void populateProductsFromEmptyInventory() {
        Long product1Id = 1L;
        Long product2Id = 2L;
        Long product3Id = 3L;

        int product1Requested = 100;
        int product2Requested = 200;
        int product3Requested = 300;

        int product1Amount = 0;
        int product2Amount = 0;
        int product3Amount = 0;

        InventoryOrder inventoryOrder = InventoryOrder
                .builder()
                .products(List.of(
                        InventoryOrderProduct
                                .builder()
                                .productId(product1Id)
                                .quantityRequested(product1Requested)
                                .quantityReceived(0)
                                .build(),

                        InventoryOrderProduct
                                .builder()
                                .productId(product2Id)
                                .quantityRequested(product2Requested)
                                .quantityReceived(0)
                                .build(),

                        InventoryOrderProduct
                                .builder()
                                .productId(product3Id)
                                .quantityRequested(product3Requested)
                                .quantityReceived(0)
                                .build()
                ))
                .build();

        List<InventoryProduct> inventoryProducts = List.of(
                InventoryProduct
                        .builder()
                        .productId(product1Id)
                        .amount(product1Amount)
                        .build(),

                InventoryProduct
                        .builder()
                        .productId(product2Id)
                        .amount(product2Amount)
                        .build(),

                InventoryProduct
                        .builder()
                        .productId(product3Id)
                        .amount(product3Amount)
                        .build()
        );

        when(inventoryProductService.findByProductIds(any())).thenReturn(inventoryProducts);

        inventoryOrderService.populateFromStoredProducts(inventoryOrder);

        verify(inventoryProductService, never()).update(any());

        assertThat(inventoryOrder.getProducts().get(0).getQuantityReceived()).isEqualTo(0);
        assertThat(inventoryOrder.getProducts().get(1).getQuantityReceived()).isEqualTo(0);
        assertThat(inventoryOrder.getProducts().get(2).getQuantityReceived()).isEqualTo(0);

        assertThat(inventoryProducts.get(0).getAmount()).isEqualTo(0);
        assertThat(inventoryProducts.get(1).getAmount()).isEqualTo(0);
        assertThat(inventoryProducts.get(2).getAmount()).isEqualTo(0);
    }

    @Test
    void populateProductsFromInventoryPartialAmount() {
        Long product1Id = 1L;
        Long product2Id = 2L;
        Long product3Id = 3L;

        int product1Requested = 100;
        int product2Requested = 200;
        int product3Requested = 300;

        int product1Amount = 0;
        int product2Amount = 400;
        int product3Amount = 500;

        InventoryOrder inventoryOrder = InventoryOrder
                .builder()
                .products(List.of(
                        InventoryOrderProduct
                                .builder()
                                .productId(product1Id)
                                .quantityRequested(product1Requested)
                                .quantityReceived(0)
                                .build(),

                        InventoryOrderProduct
                                .builder()
                                .productId(product2Id)
                                .quantityRequested(product2Requested)
                                .quantityReceived(0)
                                .build(),

                        InventoryOrderProduct
                                .builder()
                                .productId(product3Id)
                                .quantityRequested(product3Requested)
                                .quantityReceived(0)
                                .build()
                ))
                .build();

        List<InventoryProduct> inventoryProducts = List.of(
                InventoryProduct
                        .builder()
                        .productId(product1Id)
                        .amount(product1Amount)
                        .build(),

                InventoryProduct
                        .builder()
                        .productId(product2Id)
                        .amount(product2Amount)
                        .build(),

                InventoryProduct
                        .builder()
                        .productId(product3Id)
                        .amount(product3Amount)
                        .build()
        );

        when(inventoryProductService.findByProductIds(any())).thenReturn(inventoryProducts);

        inventoryOrderService.populateFromStoredProducts(inventoryOrder);

        verify(inventoryProductService, times(2)).update(any());

        assertThat(inventoryOrder.getProducts().get(0).getQuantityReceived()).isEqualTo(0);
        assertThat(inventoryOrder.getProducts().get(1).getQuantityReceived()).isEqualTo(product2Requested);
        assertThat(inventoryOrder.getProducts().get(2).getQuantityReceived()).isEqualTo(product3Requested);

        assertThat(inventoryProducts.get(0).getAmount()).isEqualTo(0);
        assertThat(inventoryProducts.get(1).getAmount()).isEqualTo(product2Amount - product2Requested);
        assertThat(inventoryProducts.get(2).getAmount()).isEqualTo(product3Amount - product3Requested);
    }

    @Test
    void populateProductsFromInventoryAmountLessRequested() {
        Long product1Id = 1L;
        Long product2Id = 2L;
        Long product3Id = 3L;

        int product1Requested = 600;
        int product2Requested = 500;
        int product3Requested = 400;

        int product1Amount = 300;
        int product2Amount = 200;
        int product3Amount = 100;

        InventoryOrder inventoryOrder = InventoryOrder
                .builder()
                .products(List.of(
                        InventoryOrderProduct
                                .builder()
                                .productId(product1Id)
                                .quantityRequested(product1Requested)
                                .quantityReceived(0)
                                .build(),

                        InventoryOrderProduct
                                .builder()
                                .productId(product2Id)
                                .quantityRequested(product2Requested)
                                .quantityReceived(0)
                                .build(),

                        InventoryOrderProduct
                                .builder()
                                .productId(product3Id)
                                .quantityRequested(product3Requested)
                                .quantityReceived(0)
                                .build()
                ))
                .build();

        List<InventoryProduct> inventoryProducts = List.of(
                InventoryProduct
                        .builder()
                        .productId(product1Id)
                        .amount(product1Amount)
                        .build(),

                InventoryProduct
                        .builder()
                        .productId(product2Id)
                        .amount(product2Amount)
                        .build(),

                InventoryProduct
                        .builder()
                        .productId(product3Id)
                        .amount(product3Amount)
                        .build()
        );

        when(inventoryProductService.findByProductIds(any())).thenReturn(inventoryProducts);

        inventoryOrderService.populateFromStoredProducts(inventoryOrder);

        verify(inventoryProductService, times(3)).update(any());

        assertThat(inventoryOrder.getProducts().get(0).getQuantityReceived()).isEqualTo(product1Requested - product1Amount);
        assertThat(inventoryOrder.getProducts().get(1).getQuantityReceived()).isEqualTo(product2Requested - product2Amount);
        assertThat(inventoryOrder.getProducts().get(2).getQuantityReceived()).isEqualTo(product3Requested - product3Amount);

        assertThat(inventoryProducts.get(0).getAmount()).isEqualTo(0);
        assertThat(inventoryProducts.get(1).getAmount()).isEqualTo(0);
        assertThat(inventoryProducts.get(2).getAmount()).isEqualTo(0);
    }

    @Test
    void populateProductsFromInventoryNotAllProductsExist() {
        Long product1Id = 1L;
        Long product2Id = 2L;
        Long product3Id = 3L;

        int product1Requested = 100;
        int product2Requested = 200;
        int product3Requested = 300;

        int product2Amount = 100;
        int product3Amount = 600;

        InventoryOrder inventoryOrder = InventoryOrder
                .builder()
                .products(List.of(
                        InventoryOrderProduct
                                .builder()
                                .productId(product1Id)
                                .quantityRequested(product1Requested)
                                .quantityReceived(0)
                                .build(),

                        InventoryOrderProduct
                                .builder()
                                .productId(product2Id)
                                .quantityRequested(product2Requested)
                                .quantityReceived(0)
                                .build(),

                        InventoryOrderProduct
                                .builder()
                                .productId(product3Id)
                                .quantityRequested(product3Requested)
                                .quantityReceived(0)
                                .build()
                ))
                .build();

        List<InventoryProduct> inventoryProducts = List.of(
                InventoryProduct
                        .builder()
                        .productId(product2Id)
                        .amount(product2Amount)
                        .build(),

                InventoryProduct
                        .builder()
                        .productId(product3Id)
                        .amount(product3Amount)
                        .build()
        );

        when(inventoryProductService.findByProductIds(any())).thenReturn(inventoryProducts);

        inventoryOrderService.populateFromStoredProducts(inventoryOrder);

        verify(inventoryProductService, times(2)).update(any());

        assertThat(inventoryOrder.getProducts().get(0).getQuantityReceived()).isEqualTo(0);
        assertThat(inventoryOrder.getProducts().get(1).getQuantityReceived()).isEqualTo(product2Requested - product2Amount);
        assertThat(inventoryOrder.getProducts().get(2).getQuantityReceived()).isEqualTo(product3Requested);

        assertThat(inventoryProducts.get(0).getAmount()).isEqualTo(0);
        assertThat(inventoryProducts.get(1).getAmount()).isEqualTo(product3Amount - product3Requested);
    }

    @Test
    void shouldConsiderOrderCompleted() {
        InventoryOrder inventoryOrder = InventoryOrder
                .builder()
                .products(List.of(
                        InventoryOrderProduct
                                .builder()
                                .quantityRequested(10)
                                .quantityReceived(10)
                                .build(),

                        InventoryOrderProduct
                                .builder()
                                .quantityRequested(1000)
                                .quantityReceived(1000)
                                .build(),

                        InventoryOrderProduct
                                .builder()
                                .quantityRequested(0)
                                .quantityReceived(0)
                                .build()
                ))
                .build();

        boolean isCompleted = inventoryOrderService.checkIfOrderIsCompleted(inventoryOrder);

        assertThat(isCompleted).isTrue();
    }

    @Test
    void shouldConsiderOrderNotCompleted() {
        InventoryOrder inventoryOrder = InventoryOrder
                .builder()
                .products(List.of(
                        InventoryOrderProduct
                                .builder()
                                .quantityRequested(10)
                                .quantityReceived(10)
                                .build(),

                        InventoryOrderProduct
                                .builder()
                                .quantityRequested(1000)
                                .quantityReceived(1000)
                                .build(),

                        InventoryOrderProduct
                                .builder()
                                .quantityRequested(10)
                                .quantityReceived(0)
                                .build()
                ))
                .build();

        boolean isCompleted = inventoryOrderService.checkIfOrderIsCompleted(inventoryOrder);

        assertThat(isCompleted).isFalse();
    }
}