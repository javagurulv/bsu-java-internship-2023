package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelRiskPremiumCalculatorSportActivities implements TravelRiskPremiumCalculator {
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return BigDecimal.ZERO;
    }

    @Override
    public String getIc() {
        return "TRAVEL_SPORT_ACTIVITIES";
    }
}
