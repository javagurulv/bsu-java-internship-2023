package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.LimitLevelRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LimitLevelCalculator {
    @Autowired private LimitLevelRepository limitLevelRepository;
    public BigDecimal calculate(TravelCalculatePremiumRequest request) {
        String limitLevel = request.getMedicalRiskLimitLevel();
        return limitLevelRepository.findByIc(limitLevel).get().getCoefficient();
    }
}
