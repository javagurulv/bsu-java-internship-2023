package lv.javaguru.travel.insurance.core.repositories;

import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface MedicalRiskLimitLevelRepository extends JpaRepository<MedicalRiskLimitLevel, Long> {
    @Query("SELECT mrll FROM MedicalRiskLimitLevel AS mrll WHERE medicalRiskLimitLevelIc = :ic")
    public Optional<MedicalRiskLimitLevel> findCoefficientByLimitLevelIc(@Param("ic")String ic);
}
