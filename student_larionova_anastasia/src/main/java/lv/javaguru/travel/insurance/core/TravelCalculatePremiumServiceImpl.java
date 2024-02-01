package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelCalculatePremiumRequestValidator requestValidator;
    private final DateTimeUtil dateTimeService;

    @Autowired
    public TravelCalculatePremiumServiceImpl(TravelCalculatePremiumRequestValidator requestValidator, DateTimeUtil dateTimeService) {
        this.dateTimeService = dateTimeService;
        this.requestValidator = requestValidator;
    }

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);
        return buildResponse(request, errors);
    }

    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request, List<ValidationError> errors)
    {
        if(errors.isEmpty()) {
            TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();

            response.setPersonFirstName(request.getPersonFirstName());
            response.setPersonLastName(request.getPersonLastName());
            response.setAgreementDateFrom(request.getAgreementDateFrom());
            response.setAgreementDateTo(request.getAgreementDateTo());
            response.setAgreementPrice(UnderWriting(request));

            return response;
        } else {
            return new TravelCalculatePremiumResponse(errors);
        }
    }

    private BigDecimal UnderWriting(TravelCalculatePremiumRequest request) {
        long dayBetween = dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());
        BigDecimal costOfInsurance = new BigDecimal(dayBetween * 1);
        return costOfInsurance;
    }
}
