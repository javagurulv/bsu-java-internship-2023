package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.LimitLevelRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LimitLevelCalculator {
    @Value("${medical.risk.limit.level.enabled:false}")
    private boolean medicalRiskLimitLevelEnabled;
    @Autowired private LimitLevelRepository limitLevelRepository;
    public BigDecimal calculate(TravelCalculatePremiumRequestV1 request) {
        return (medicalRiskLimitLevelEnabled)
                ? calculatePresentOptional(request)
                : BigDecimal.ONE;
    }
    private BigDecimal calculatePresentOptional(TravelCalculatePremiumRequestV1 request) {
        return limitLevelRepository.findByIc(request.getMedicalRiskLimitLevel())
                .orElseThrow(()->new RuntimeException("Optional is empty")).getCoefficient();
    }
}
