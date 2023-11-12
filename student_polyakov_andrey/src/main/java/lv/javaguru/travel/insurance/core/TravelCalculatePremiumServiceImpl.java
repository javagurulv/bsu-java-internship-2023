package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse a = new TravelCalculatePremiumResponse();
        a.setPersonFirstName(request.getPersonFirstName());
        a.setPersonLastName(request.getPersonLastName());
        a.setAgreementDateTo(request.getAgreementDateTo());
        a.setAgreementDateFrom(request.getAgreementDateFrom());
        return a;
    }

}
