package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface TravelUnderwritingProcess {
    BigDecimal calculatePremium(TravelCalculatePremiumRequest request);
}
