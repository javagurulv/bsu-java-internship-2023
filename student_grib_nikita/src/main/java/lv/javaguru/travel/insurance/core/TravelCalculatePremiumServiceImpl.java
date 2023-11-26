package lv.javaguru.travel.insurance.core;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

//@NoArgsConstructor
//@AllArgsConstructor

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {
    @Autowired private TravelCalculatePremiumRequestValidator requestValidator;
    @Autowired private TravelPremiumUnderwriting premiumUnderwriting;

    @Autowired
    public TravelCalculatePremiumServiceImpl(TravelCalculatePremiumRequestValidator requestValidator, TravelPremiumUnderwriting premiumUnderwriting) {
        this.requestValidator = requestValidator;
        this.premiumUnderwriting = premiumUnderwriting;
    }

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);
        return errors.isEmpty() ? buildResponse(request, premiumUnderwriting.calculatePremium(request)) : buildResponse(errors);
    }

    TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request, BigDecimal agreementPrice){

        TravelCalculatePremiumResponse premiumResponse = new TravelCalculatePremiumResponse();
        premiumResponse.setAgreementDateTo(request.getAgreementDateTo());
        premiumResponse.setAgreementDateFrom(request.getAgreementDateFrom());
        premiumResponse.setPersonFirstName(request.getPersonFirstName());
        premiumResponse.setPersonLastName(request.getPersonLastName());
        premiumResponse.setAgreementPrice(agreementPrice);
        return premiumResponse;
    }

}
