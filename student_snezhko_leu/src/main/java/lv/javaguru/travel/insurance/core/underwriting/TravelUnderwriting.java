package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.math.BigDecimal;

public interface TravelUnderwriting {
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request);//TravelCalculatePremiumResponse response);
}
