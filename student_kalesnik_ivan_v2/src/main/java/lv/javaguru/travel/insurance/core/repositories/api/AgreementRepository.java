package lv.javaguru.travel.insurance.core.repositories.api;

import lv.javaguru.travel.insurance.core.domain.api.AgreementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AgreementRepository extends JpaRepository<AgreementEntity, Long> {
    Optional<AgreementEntity> findByUuid(String uuid);
}
