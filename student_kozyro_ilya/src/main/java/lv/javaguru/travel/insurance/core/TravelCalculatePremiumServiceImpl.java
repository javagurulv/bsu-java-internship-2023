package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        var premResp = new TravelCalculatePremiumResponse();

        premResp.setPersonFirstName(request.getPersonFirstName());
        premResp.setPersonLastName(request.getPersonLastName());
        premResp.setAgreementDateFrom(request.getAgreementDateFrom());
        premResp.setAgreementDateTo(request.getAgreementDateTo());

        return premResp;
    }

}
