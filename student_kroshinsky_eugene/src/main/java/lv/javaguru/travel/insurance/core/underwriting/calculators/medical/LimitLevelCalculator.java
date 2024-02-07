package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.LimitLevel;
import lv.javaguru.travel.insurance.core.repositories.LimitLevelRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class LimitLevelCalculator {
    @Value("${medical.risk.limit.level.enabled:false}")
    private boolean medicalRiskLimitLevelEnabled;
    @Autowired private LimitLevelRepository limitLevelRepository;
    public BigDecimal calculate(TravelCalculatePremiumRequest request) {
        if (medicalRiskLimitLevelEnabled) {
            String requestLimitLevel = request.getMedicalRiskLimitLevel();
            Optional<LimitLevel> limitLevel = limitLevelRepository.findByIc(requestLimitLevel);
            return limitLevel.orElseThrow(()->new RuntimeException("Optional is empty")).getCoefficient();
        } else {
            return BigDecimal.ONE;
        }
    }
}
