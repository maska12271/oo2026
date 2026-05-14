package ee.mihkel.veebipood.repository;

import ee.mihkel.veebipood.entity.Person;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<@NonNull Person,@NonNull Long> {
    Person findByEmail(String email);
}
