package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Autowired
    DateTimeService dateTimeService;
    @Autowired
    TravelCalculatorPremiumRequestValidator validator;
    @Autowired
    PremiumUnderwritingService underwritingService;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {

        List<ValidationError> errors = validator.validate(request);

        if(!errors.isEmpty()) {
            return buildResponse(errors);
        }
        return buildResponse(request, underwritingService.calculatePremium(request));
    }

    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request, BigDecimal price) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(price);

        return response;
    }

    private TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

}
