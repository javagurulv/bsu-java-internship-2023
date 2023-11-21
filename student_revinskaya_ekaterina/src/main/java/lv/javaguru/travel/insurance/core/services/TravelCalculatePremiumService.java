package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

public interface TravelCalculatePremiumService {
    TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request);

}
