package lv.javaguru.travel.insurance.core.repositories.entity;

import lv.javaguru.travel.insurance.core.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository  extends JpaRepository<Person, Long> {
    @Query("SELECT person from Person person " +
            "where person.personalCode = :personalCode" )
    Optional<Person> findByPersonalCode(
            @Param("personalCode") String personCode
    );
}
