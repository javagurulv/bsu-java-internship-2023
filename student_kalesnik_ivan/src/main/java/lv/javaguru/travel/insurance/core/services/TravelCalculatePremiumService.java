package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.validation.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.validation.v1.TravelCalculatePremiumResponseV1;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponseV1 calculatePremium(TravelCalculatePremiumRequestV1 request);

}
