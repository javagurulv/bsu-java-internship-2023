package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse result = new TravelCalculatePremiumResponse();
        changeResponsePersonFirstName(request, result);
        changeResponsePersonLastName(request, result);
        changeResponseAgreementDateFrom(request, result);
        changeResponseAgreementDateTo(request, result);
        return result;
    }
    public void changeResponsePersonFirstName(TravelCalculatePremiumRequest request, TravelCalculatePremiumResponse response) {
        response.setPersonFirstName(request.getPersonFirstName());
    }
    public void changeResponsePersonLastName(TravelCalculatePremiumRequest request, TravelCalculatePremiumResponse response) {
        response.setPersonLastName(request.getPersonLastName());
    }
    public void changeResponseAgreementDateFrom(TravelCalculatePremiumRequest request, TravelCalculatePremiumResponse response) {
        response.setAgreementDateFrom(request.getAgreementDateFrom());
    }
    public void changeResponseAgreementDateTo(TravelCalculatePremiumRequest request, TravelCalculatePremiumResponse response) {
        response.setAgreementDateTo(request.getAgreementDateTo());
    }
}
