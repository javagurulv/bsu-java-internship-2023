package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.TMMedicalRiskLimitLevel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TMMedicalRiskLimitLevelRepository
        extends JpaRepository<TMMedicalRiskLimitLevel, Long> {

    @Cacheable(cacheNames = {"tmMedicalRiskLimitLevelCache"}, key = "#p0", unless="#result == null")
    Optional<TMMedicalRiskLimitLevel> findByMedicalRiskLimitLevelIc(String medicalRiskLimitLevelIc);

}
