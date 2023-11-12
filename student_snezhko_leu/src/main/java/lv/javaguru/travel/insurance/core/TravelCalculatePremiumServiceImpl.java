package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse result = new TravelCalculatePremiumResponse();
        result.setPersonFirstName(request.getPersonFirstName());
        result.setPersonLastName(request.getPersonLastName());
        result.setAgreementDateFrom(request.getAgreementDateFrom());
        result.setAgreementDateTo(request.getAgreementDateTo());
        return result;
    }

}
