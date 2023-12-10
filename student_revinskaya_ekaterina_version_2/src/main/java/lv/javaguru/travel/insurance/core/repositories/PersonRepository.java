package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository  extends JpaRepository<Person, Long> {
    @Query("SELECT person from Person person " +
            "where person.firstName = :firstName " +
            "and person.lastName = :lastName " +
            "and person.personalCode = :personalCode" )
    Optional<Person> findBy(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("personalCode") String personCode
    );
}
