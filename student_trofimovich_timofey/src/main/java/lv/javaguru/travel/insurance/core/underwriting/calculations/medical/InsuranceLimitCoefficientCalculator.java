package lv.javaguru.travel.insurance.core.underwriting.calculations.medical;


import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
class InsuranceLimitCoefficientCalculator {
    @Value("${medical.risk.limit.level.enabled}")
    private boolean limitLevelIsEnabled;
    @Autowired
    private MedicalRiskLimitLevelRepository repository;



    BigDecimal getInsuranceLimitCoefficient(TravelCalculatePremiumRequestV1 request) {
        return limitLevelIsEnabled ? getCoefficientFromDB(request) : getDefaultCoefficient();
    }
    private BigDecimal getCoefficientFromDB(TravelCalculatePremiumRequestV1 request) {
        Optional<MedicalRiskLimitLevel> limitLevel = repository.findByMedicalRiskLimitLevelIc(request.getMedicalRiskLimitLevel());
        return limitLevel.map(MedicalRiskLimitLevel::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Insurance limit level coefficient not found for limit level ic: " + request.getMedicalRiskLimitLevel()));
    }
    private  BigDecimal getDefaultCoefficient() {
        return BigDecimal.ONE;
    }
}
