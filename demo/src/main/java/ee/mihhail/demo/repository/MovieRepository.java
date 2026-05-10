package ee.mihhail.demo.repository;

import ee.mihhail.demo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Movie, Long> {
}
