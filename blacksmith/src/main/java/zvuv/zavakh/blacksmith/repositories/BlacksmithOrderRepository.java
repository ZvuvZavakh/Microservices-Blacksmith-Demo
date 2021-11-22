package zvuv.zavakh.blacksmith.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zvuv.zavakh.blacksmith.domain.BlacksmithOrder;

import java.util.Optional;

@Repository
public interface BlacksmithOrderRepository extends JpaRepository<BlacksmithOrder, Long> {

    Optional<BlacksmithOrder> findTopByOrderByIdDesc();
}
