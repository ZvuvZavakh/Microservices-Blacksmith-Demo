package zvuv.zavakh.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zvuv.zavakh.products.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
