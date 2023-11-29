package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class RiskLimitLevelCalculator {

    @Value( "${medical.risk.limit.level.enabled:false}" )
    private Boolean medicalRiskLimitLevelEnabled;

    @Autowired private MedicalRiskLimitLevelRepository riskLimitLevelRepository;

    BigDecimal calculate(TravelCalculatePremiumRequestV1 request) {
        return medicalRiskLimitLevelEnabled
                ? getCoefficient(request)
                : getDefaultValue();
    }

    private BigDecimal getCoefficient(TravelCalculatePremiumRequestV1 request) {
        return riskLimitLevelRepository.findByMedicalRiskLimitLevelIc(request.getMedicalRiskLimitLevel())
                .map(MedicalRiskLimitLevel::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Medical risk limit level not found by = " + request.getMedicalRiskLimitLevel()));
    }

    private static BigDecimal getDefaultValue() {
        return BigDecimal.ONE;
    }

}
