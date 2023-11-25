package lv.javaguru.travel.insurance.core.underwriting;


import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelPremiumCalculatorResult;
public interface TravelPremiumUnderwriting {
    TravelPremiumCalculatorResult calculatePremium(TravelCalculatePremiumRequest request);

}
