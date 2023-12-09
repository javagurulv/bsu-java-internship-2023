package lv.javaguru.travel.insurance.core.repositories.api;

import lv.javaguru.travel.insurance.core.domain.api.PolisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolisRepository extends JpaRepository<PolisEntity, Long> {
    //
}
