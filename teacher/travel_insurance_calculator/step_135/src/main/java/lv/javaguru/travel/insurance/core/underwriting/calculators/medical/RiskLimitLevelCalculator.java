package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.TMMedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.TMMedicalRiskLimitLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class RiskLimitLevelCalculator {

    @Value( "${medical.risk.limit.level.enabled:false}" )
    private Boolean medicalRiskLimitLevelEnabled;

    @Autowired private TMMedicalRiskLimitLevelRepository riskLimitLevelRepository;

    BigDecimal calculate(PersonDTO person) {
        return medicalRiskLimitLevelEnabled
                ? getCoefficient(person)
                : getDefaultValue();
    }

    private BigDecimal getCoefficient(PersonDTO person) {
        return riskLimitLevelRepository.findByMedicalRiskLimitLevelIc(person.getMedicalRiskLimitLevel())
                .map(TMMedicalRiskLimitLevel::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Medical risk limit level not found by = " + person.getMedicalRiskLimitLevel()));
    }

    private static BigDecimal getDefaultValue() {
        return BigDecimal.ONE;
    }

}
