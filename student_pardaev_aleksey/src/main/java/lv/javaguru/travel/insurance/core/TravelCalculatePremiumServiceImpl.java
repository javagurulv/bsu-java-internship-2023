package lv.javaguru.travel.insurance.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lv.javaguru.travel.insurance.dto.*;
import java.math.BigDecimal;
import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelCalculatePremiumRequestValidator requestValidator;

    private final DateTimeService dateTimeService;

    @Autowired
    TravelCalculatePremiumServiceImpl(TravelCalculatePremiumRequestValidator requestValidator, DateTimeService dateTimeService) {
        this.requestValidator = requestValidator;
        this.dateTimeService = dateTimeService;
    }

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);
        return buildResponse(request, errors);
    }

    public TravelCalculatePremiumResponse buildResponse (TravelCalculatePremiumRequest request, List<ValidationError> errors){
        if(!errors.isEmpty()){
            return new TravelCalculatePremiumResponse(errors);
        }

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
