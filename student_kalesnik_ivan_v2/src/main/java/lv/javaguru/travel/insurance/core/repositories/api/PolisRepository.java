package lv.javaguru.travel.insurance.core.repositories.api;

import lv.javaguru.travel.insurance.core.domain.api.AgreementEntity;
import lv.javaguru.travel.insurance.core.domain.api.AgreementPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PolisRepository extends JpaRepository<AgreementPersonEntity, Long> {
    List<AgreementPersonEntity> findByAgreement(AgreementEntity agreement);
}
