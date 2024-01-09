package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelRiskPremiumCalculatorCancellation implements TravelRiskPremiumCalculator{
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return BigDecimal.ZERO;
    }

    @Override
    public String getIc() {
        return "TRAVEL_CANCELLATION";
    }
}
