package lv.javaguru.travel.insurance.core.repositories.api;

import lv.javaguru.travel.insurance.core.domain.api.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, BigDecimal> {
    @Query("SELECT pe from PersonEntity pe " +
            "where pe.firstName = :firstName " +
            "      and pe.lastName = :lastName " +
            "      and pe.personCode = :personCode")
    Optional<PersonEntity> findBy(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("personCode") String personCode
    );
}
