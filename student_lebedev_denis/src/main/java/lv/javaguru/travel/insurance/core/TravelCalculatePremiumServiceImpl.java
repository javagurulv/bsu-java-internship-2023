package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.validators.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.validators.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelCalculatePremiumRequestValidator requestValidator;
    private final TravelPremiumUnderwriting underwriting;

    @Autowired
    public TravelCalculatePremiumServiceImpl(TravelCalculatePremiumRequestValidator requestValidator,
                                             TravelPremiumUnderwriting underwriting) {
        this.requestValidator = requestValidator;
        this.underwriting = underwriting;
    }

    private TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request, BigDecimal agreementPrice) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(agreementPrice);
        return response;
    }

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);
        return errors.isEmpty()
                ? buildResponse(request, underwriting.calculatePremium(request))
                : buildResponse(errors);
    }
}
