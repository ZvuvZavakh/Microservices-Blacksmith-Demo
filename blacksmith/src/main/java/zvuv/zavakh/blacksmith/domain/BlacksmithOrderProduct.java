package zvuv.zavakh.blacksmith.domain;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "blacksmith_order_products")
public class BlacksmithOrderProduct {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productId;
    private Integer quantity;

    @ManyToOne
    private BlacksmithOrder blacksmithOrder;
}
