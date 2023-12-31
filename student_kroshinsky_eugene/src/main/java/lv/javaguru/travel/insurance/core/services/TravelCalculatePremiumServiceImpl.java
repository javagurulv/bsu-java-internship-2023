package lv.javaguru.travel.insurance.core.services;

import lv.javaguru.travel.insurance.core.utils.DateTimeUtil;
import lv.javaguru.travel.insurance.rest.loggers.TravelCalculatePremiumRequestLogger;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {
    @Autowired private DateTimeUtil dateTimeService;
    @Autowired private TravelCalculatePremiumRequestValidator requestValidator;
    @Autowired private TravelCalculatePremiumRequestLogger logger;
    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);
        return errors.isEmpty() ? buildResponse(request) : buildResponse(errors);
    }
    private TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors){
        return new TravelCalculatePremiumResponse(errors);
    }
    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request){
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(dateTimeService.calculateDateDifference(request.getAgreementDateFrom(),request.getAgreementDateTo()));
        return response;
    }
}
