package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import lv.javaguru.travel.insurance.core.util.MedicalRiskLimitLevelEnabledUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InsuranceLimitCoefficientCalculator {
    @Autowired
    private MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;
@Autowired
private MedicalRiskLimitLevelEnabledUtil medicalRiskLimitLevelEnabledUtil;
    BigDecimal calculate(TravelCalculatePremiumRequest request){
        return medicalRiskLimitLevelEnabledUtil.isMedicalRiskLimitLevelEnabled()
                ? calculateValue(request)
                : defaulterValue();

    }
    private BigDecimal defaulterValue(){
        return BigDecimal.ONE;
    }
    private BigDecimal calculateValue(TravelCalculatePremiumRequest request){
        return medicalRiskLimitLevelRepository
                .findByMedicalRiskLimitLevelIc(request.getMedicalRiskLimitLevel())
                .orElseThrow(()->new RuntimeException(
                        "medicalRiskLimitLevel with ic "+request.getMedicalRiskLimitLevel()+" not found"))
                .getCoefficient();
    }
}
