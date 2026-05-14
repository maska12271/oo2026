package ee.mihkel.veebipood.repository;

import ee.mihkel.veebipood.entity.Category;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<@NonNull Category,@NonNull Long> {
}
