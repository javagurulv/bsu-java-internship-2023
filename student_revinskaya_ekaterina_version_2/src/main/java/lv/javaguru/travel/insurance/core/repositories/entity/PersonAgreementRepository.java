package lv.javaguru.travel.insurance.core.repositories.entity;

import lv.javaguru.travel.insurance.core.domain.entity.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entity.PersonAgreementEntity;
import lv.javaguru.travel.insurance.core.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonAgreementRepository extends JpaRepository<PersonAgreementEntity, Long> {
    @Query("SELECT pa from PersonAgreementEntity pa "
            + "where pa.personEntityId = :personEntityId "
            + "and pa.agreementEntityId = :agreementEntityId"
    )
    Optional<PersonAgreementEntity> findByPersonIdAndAgreementId(@Param("personEntityId") PersonEntity personEntityId,
                                                                 @Param("agreementEntityId") AgreementEntity agreementEntityId);
    @Query("SELECT pa from PersonAgreementEntity pa "
            + "WHERE pa.agreementEntityId = :agreementEntityId"
    )
    List<PersonAgreementEntity> findByAgreementId(@Param("agreementEntityId") AgreementEntity agreementEntityId);

}
