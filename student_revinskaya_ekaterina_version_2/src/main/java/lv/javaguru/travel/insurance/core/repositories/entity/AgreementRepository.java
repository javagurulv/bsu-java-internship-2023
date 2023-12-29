package lv.javaguru.travel.insurance.core.repositories.entity;

import lv.javaguru.travel.insurance.core.domain.entity.Agreement;
import lv.javaguru.travel.insurance.core.domain.entity.Person;
import lv.javaguru.travel.insurance.core.domain.entity.PersonAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    @Query("SELECT CASE WHEN COUNT(agr) != 0 THEN true ELSE false END "
            + "FROM Agreement agr WHERE agr.uuid = :uuid"
    )
    boolean existByUuid(@Param("uuid") String uuid);

    @Query("SELECT ag from Agreement ag "
            + "where ag.uuid = :uuid "
    )
    Optional<Agreement> findByUuid(@Param("uuid") String uuid);
}
