package lv.javaguru.travel.insurance.core.underwriting;


import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.TravelPremiumCalculatorResult;

public interface TravelPremiumUnderwriting {
    TravelPremiumCalculatorResult calculatePremium(TravelCalculatePremiumRequestV1 request);

}
