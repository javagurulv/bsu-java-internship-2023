package lv.javaguru.travel.insurance.core.underwriting;


import lv.javaguru.travel.insurance.validation.v1.TravelCalculatePremiumRequestV1;


public
interface TravelPremiumUnderwriting {

    TravelPremiumCalculationResult calculatePremium(TravelCalculatePremiumRequestV1 request);

}
