package lv.javaguru.travel.insurance.core.repositories;


import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalRiskLimitLevelRepository extends JpaRepository<MedicalRiskLimitLevel, Long> {

    @Cacheable(cacheNames = {"medicalRiskLimitLevelCache"}, key = "#medicalRiskLimitLevelIc", condition="#result != null")
    Optional<MedicalRiskLimitLevel> findByMedicalRiskLimitLevelIc(String medicalRiskLimitLevelIc);
}
