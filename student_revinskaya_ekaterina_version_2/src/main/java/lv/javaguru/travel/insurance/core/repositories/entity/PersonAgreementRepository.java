package lv.javaguru.travel.insurance.core.repositories.entity;

import lv.javaguru.travel.insurance.core.domain.entity.Agreement;
import lv.javaguru.travel.insurance.core.domain.entity.Person;
import lv.javaguru.travel.insurance.core.domain.entity.PersonAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PersonAgreementRepository extends JpaRepository<PersonAgreement, Long> {
    @Query("SELECT pa from PersonAgreement pa "
            + "where pa.personId = :personId "
            + "and pa.agreementId = :agreementId"
    )
    Optional<PersonAgreement> findByPersonIdAndAgreementId(@Param("personId") Person personId,
                                                           @Param("agreementId")Agreement agreementId);

}
