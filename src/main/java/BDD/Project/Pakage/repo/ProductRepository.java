package BDD.Project.Pakage.repo;

import BDD.Project.Pakage.model.Order;
import BDD.Project.Pakage.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
