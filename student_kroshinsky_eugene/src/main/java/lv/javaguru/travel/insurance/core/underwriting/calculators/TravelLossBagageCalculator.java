package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
class TravelLossBagageCalculator implements TravelRiskPremiumCalculator {
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return new BigDecimal(6);
    }
    public String getRiskIc() {
        return "TRAVEL_LOSS_BAGGAGE";
    }
}
