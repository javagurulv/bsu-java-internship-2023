package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.PersonDTODomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonDTODomain, Long> {

    @Query("SELECT p FROM PersonDTODomain p" +
            " WHERE p.personFirstName = :fn" +
            " AND p.personLastName = :ln" +
            " AND p.personIc = :i")
    Optional<PersonDTODomain> findBy(@Param("fn") String firstName,
                                            @Param("ln") String lastName,
                                            @Param("i") String ic
    );
}
