package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    private TravelCalculatePremiumRequestValidator validator = new TravelCalculatePremiumRequestValidator();
    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = validator.validate(request);
        return !errors.isEmpty()
                ? buildResponse(errors)
                : buildResponse(request);
    }
    public TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        //response.initAgreementPrice();
        TravelUnderwriting underwriting = new TravelUnderwriting();
        response.setAgreementPrice(underwriting.calculatePremium(response));
        return response;
    }
    public TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }
}
