package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;

import java.math.BigDecimal;

public interface TravelUnderwriting {
    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request);//TravelCalculatePremiumResponse response);
    //public List<TravelRiskPremiumCalculator> getRiskCalculators();
}
