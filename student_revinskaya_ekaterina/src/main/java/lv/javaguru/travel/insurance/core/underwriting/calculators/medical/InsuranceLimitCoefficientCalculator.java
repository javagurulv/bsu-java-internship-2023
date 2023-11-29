package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
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
    BigDecimal calculate(TravelCalculatePremiumRequestV1 request){
        return medicalRiskLimitLevelEnabled
                ? calculateValue(request)
                : defaulterValue();

    }
    private BigDecimal defaulterValue(){
        return BigDecimal.ONE;
    }
    private BigDecimal calculateValue(TravelCalculatePremiumRequestV1 request){
        return medicalRiskLimitLevelRepository
                .findByMedicalRiskLimitLevelIc(request.getMedicalRiskLimitLevel())
                .orElseThrow(()->new RuntimeException(
                        "medicalRiskLimitLevel with ic "+request.getMedicalRiskLimitLevel()+" not found"))
                .getCoefficient();
    }
}
