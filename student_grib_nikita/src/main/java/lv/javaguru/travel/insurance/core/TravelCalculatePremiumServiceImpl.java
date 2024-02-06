package lv.javaguru.travel.insurance.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
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
@JsonFormat
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {
    @Autowired private TravelCalculatePremiumRequestValidator requestValidator;
    @Autowired private TravelPremiumUnderwriting premiumUnderwriting;

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
