package promaxject.what_tv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import promaxject.what_tv.domain.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
