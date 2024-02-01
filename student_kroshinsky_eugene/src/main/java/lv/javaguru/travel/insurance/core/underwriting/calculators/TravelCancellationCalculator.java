package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
class TravelCancellationCalculator implements TravelRiskPremiumCalculator {
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return BigDecimal.ONE;
    }
    public String getRiskIc() {
        return "TRAVEL_CANCELLATION";
    }
}
