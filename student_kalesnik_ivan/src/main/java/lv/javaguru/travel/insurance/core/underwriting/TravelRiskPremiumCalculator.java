package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.validation.v1.TravelCalculatePremiumRequestV1;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {
    BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request);
    String getRiskIc();
}
