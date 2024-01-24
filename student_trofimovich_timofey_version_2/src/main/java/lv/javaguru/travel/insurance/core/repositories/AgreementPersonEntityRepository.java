package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.entities.AgreementPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AgreementPersonEntityRepository extends JpaRepository<AgreementPersonEntity, Long> {
    @Query("""
    SELECT ape from AgreementPersonEntity ape
    LEFT join ape.agreement as a
    where a.uuid = :uuid
    """)
    List<AgreementPersonEntity> findByAgreement_Uuid(String uuid);


}
