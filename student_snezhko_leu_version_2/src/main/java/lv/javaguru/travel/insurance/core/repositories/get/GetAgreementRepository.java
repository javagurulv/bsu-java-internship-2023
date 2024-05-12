package lv.javaguru.travel.insurance.core.repositories.get;

import lv.javaguru.travel.insurance.core.domain.agreement.AgreementEntityDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

public interface GetAgreementRepository extends JpaRepository<AgreementEntityDomain, Long> {
    public Optional<AgreementEntityDomain> findByUuid(UUID uuid);
}
