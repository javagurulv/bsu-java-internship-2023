package lv.javaguru.travel.insurance.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.javaguru.travel.insurance.dto.*;
import java.math.BigDecimal;
import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired private TravelCalculatePremiumRequestValidator requestValidator;
    @Autowired private TravelPremiumUnderwriting underwriting;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);
        return errors.isEmpty()
                ? buildResponse(request, underwriting.calculatePremium(request))
                : new TravelCalculatePremiumResponse(errors);
    }

    public TravelCalculatePremiumResponse buildResponse (TravelCalculatePremiumRequest request, BigDecimal premium){

        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(premium);

        return response;
    }

}
