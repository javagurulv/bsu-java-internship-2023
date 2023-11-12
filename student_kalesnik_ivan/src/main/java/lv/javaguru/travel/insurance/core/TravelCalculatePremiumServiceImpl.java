package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {
    @Autowired
    private DateTimeService dateTimeService;
    @Autowired
    private TravelCalculatePremiumRequestValidator requestValidator;
    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        //Validation
        List<ValidationError> errors = requestValidator.validate(request);
        if (!errors.isEmpty()) {
            return new TravelCalculatePremiumResponse(errors);
        }

        //Body
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        var daysBetween = dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());
        response.setAgreementPrice(new BigDecimal(daysBetween));
        return response;
    }

}
