package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InsuranceLimitCoefficientCalculator {
    @Autowired
    private MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;
    @Value( "${medical.risk.limit.level.enabled:false}" )
    private Boolean medicalRiskLimitLevelEnabled;
    BigDecimal calculate(AgreementDTO agreement){
        return medicalRiskLimitLevelEnabled
                ? calculateValue(agreement)
                : defaulterValue();

    }
    private BigDecimal defaulterValue(){
        return BigDecimal.ONE;
    }
    private BigDecimal calculateValue(AgreementDTO agreement){
        return medicalRiskLimitLevelRepository
                .findByMedicalRiskLimitLevelIc(agreement.getMedicalRiskLimitLevel())
                .orElseThrow(()->new RuntimeException(
                        "medicalRiskLimitLevel with ic "+agreement.getMedicalRiskLimitLevel()+" not found"))
                .getCoefficient();
    }
}
