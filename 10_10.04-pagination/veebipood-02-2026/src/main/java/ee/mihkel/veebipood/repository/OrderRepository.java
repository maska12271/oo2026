package ee.mihkel.veebipood.repository;

import ee.mihkel.veebipood.entity.Order;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<@NonNull Order,@NonNull Long> {
}
