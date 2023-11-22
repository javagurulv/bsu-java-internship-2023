package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {
    BigDecimal calculatePremium(TravelCalculatePremiumRequest request);
    String getRiskIc();
}
