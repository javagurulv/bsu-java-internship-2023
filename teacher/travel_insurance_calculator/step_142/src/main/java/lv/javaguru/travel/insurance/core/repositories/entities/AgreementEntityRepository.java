package lv.javaguru.travel.insurance.core.repositories.entities;

import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgreementEntityRepository extends JpaRepository<AgreementEntity, Long> {

    Optional<AgreementEntity> findByUuid(String uuid);

}
