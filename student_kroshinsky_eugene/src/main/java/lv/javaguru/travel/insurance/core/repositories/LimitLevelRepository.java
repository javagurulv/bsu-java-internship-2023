package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.LimitLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LimitLevelRepository extends JpaRepository<LimitLevel, Long> {
    Optional<LimitLevel> findByIc(String countryIc);
}