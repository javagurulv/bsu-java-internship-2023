package lv.javaguru.travel.insurance.core.repositories.entities;

import lv.javaguru.travel.insurance.core.domain.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonEntityRepository extends JpaRepository<PersonEntity, Long> {

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
