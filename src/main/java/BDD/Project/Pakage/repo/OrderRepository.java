package BDD.Project.Pakage.repo;

import BDD.Project.Pakage.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
