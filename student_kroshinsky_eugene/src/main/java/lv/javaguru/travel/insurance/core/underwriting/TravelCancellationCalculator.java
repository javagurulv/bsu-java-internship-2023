package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class TravelCancellationCalculator implements TravelRiskPremiumCalculator{
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return BigDecimal.ONE;
    }
    public String getRiskIc() {
        return "TRAVEL_CANCELLATION";
    }
}
