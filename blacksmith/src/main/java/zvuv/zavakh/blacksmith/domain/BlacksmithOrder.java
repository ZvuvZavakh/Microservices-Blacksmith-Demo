package zvuv.zavakh.blacksmith.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "blacksmith_orders")
public class BlacksmithOrder {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "blacksmith_order_id")
    private List<BlacksmithOrderProduct> products;
}
