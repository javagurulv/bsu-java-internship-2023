package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TMMedicalRiskLimitLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TMMedicalRiskLimitLevelRepository extends JpaRepository<TMMedicalRiskLimitLevel, Long> {
    Optional<TMMedicalRiskLimitLevel> findByMedicalRiskLimitLevelIc(String medicalRiskLimitLevelIc);
}
