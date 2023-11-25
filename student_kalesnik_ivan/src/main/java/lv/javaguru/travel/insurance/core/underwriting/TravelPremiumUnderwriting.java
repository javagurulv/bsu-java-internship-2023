package lv.javaguru.travel.insurance.core.underwriting;


import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;


import java.math.BigDecimal;


public
interface TravelPremiumUnderwriting {

    TravelPremiumCalculationResult calculatePremium(TravelCalculatePremiumRequest request);

}
