package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.LimitLevelRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LimitLevelCalculator {
    @Value("${medical.risk.limit.level.enabled:false}")
    private boolean medicalRiskLimitLevelEnabled;
    @Autowired private LimitLevelRepository limitLevelRepository;
    public BigDecimal calculate(TravelCalculatePremiumRequest request) {
        if (medicalRiskLimitLevelEnabled) {
            String limitLevel = request.getMedicalRiskLimitLevel();
            return limitLevelRepository.findByIc(limitLevel).get().getCoefficient();
        } else {
            return BigDecimal.ONE;
        }
    }
}
