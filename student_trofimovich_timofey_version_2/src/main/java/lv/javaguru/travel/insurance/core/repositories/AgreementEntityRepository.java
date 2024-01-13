package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.entities.AgreementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementEntityRepository extends JpaRepository<AgreementEntity, Long> {

}
