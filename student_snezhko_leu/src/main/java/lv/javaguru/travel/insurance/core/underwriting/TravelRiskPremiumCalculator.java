package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {
    BigDecimal calculatePremium(TravelCalculatePremiumRequest request);
    String getIc();
}
