package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class TravelSportCalculator implements TravelRiskPremiumCalculator {
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return new BigDecimal(5);
    }
    public String getRiskIc() {
        return "TRAVEL_SPORT_ACTIVITIES";
    }
}
