package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonRiskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AgreementPersonRiskEntityRepository extends JpaRepository<AgreementPersonRiskEntity, Long> {
    @Query("""
    SELECT apre from AgreementPersonRiskEntity apre
    LEFT join apre.agreementPersonEntity as ape
    where ape.person.personCode = :personCode
    and ape.person.firstName = :firstName
    and ape.person.lastName = :lastName
    and ape.id = :id
    """)
    List<AgreementPersonRiskEntity> findByAgreementAndPersonUniqueInfo(String personCode, String firstName, String lastName, Long id);

}