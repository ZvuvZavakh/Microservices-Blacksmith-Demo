package zvuv.zavakh.customers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zvuv.zavakh.customers.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
