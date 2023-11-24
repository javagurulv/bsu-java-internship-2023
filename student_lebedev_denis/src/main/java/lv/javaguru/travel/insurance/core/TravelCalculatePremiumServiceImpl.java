package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.validators.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.validators.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired private TravelCalculatePremiumRequestValidator requestValidator;
    @Autowired private DateTimeService dateTimeService;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);
        if (!errors.isEmpty()) {
            return new TravelCalculatePremiumResponse(errors);
        }

        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse(
                request.getPersonFirstName(), request.getPersonLastName(),
                request.getAgreementDateFrom(), request.getAgreementDateTo());
        response.setAgreementPrice(new BigDecimal(dateTimeService.getDays(request.getAgreementDateFrom(), request.getAgreementDateTo())));
        return response;
    }
}
