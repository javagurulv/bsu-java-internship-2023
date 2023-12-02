package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {
    default BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request){
        return BigDecimal.ZERO;
    }
    String getRiskIc();
}
