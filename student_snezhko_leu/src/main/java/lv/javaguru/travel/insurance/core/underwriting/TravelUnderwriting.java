package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;
import java.util.List;

public interface TravelUnderwriting {
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request);//TravelCalculatePremiumResponse response);
    //public List<TravelRiskPremiumCalculator> getRiskCalculators();
}
