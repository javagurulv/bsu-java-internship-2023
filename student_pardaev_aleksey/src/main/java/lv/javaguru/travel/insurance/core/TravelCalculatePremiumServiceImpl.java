package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse temp = new TravelCalculatePremiumResponse();
        temp.setPersonFirstName(request.getPersonFirstName());
        temp.setPersonLastName(request.getPersonLastName());
        temp.setAgreementDateFrom(request.getAgreementDateFrom());
        temp.setAgreementDateTo(request.getAgreementDateTo());
        return temp;
    }

}
