package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
class TravelCancellationCalculator implements TravelRiskPremiumCalculator {
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request) {
        return BigDecimal.ONE;
    }
    @Override
    public String getRiskIc() {
        return "TRAVEL_CANCELLATION";
    }
}
