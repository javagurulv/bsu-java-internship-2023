package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {
    default BigDecimal calculatePremium(TravelCalculatePremiumRequest request){
        return BigDecimal.ZERO;
    }
    String getRiskIc();
}
