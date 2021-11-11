package zvuv.zavakh.inventory.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "inventory_order_products")
public class InventoryOrderProduct {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productId;
    private Integer quantityRequested;
    private Integer quantityReceived = 0;

    @ManyToOne
    private InventoryOrder order;
}
