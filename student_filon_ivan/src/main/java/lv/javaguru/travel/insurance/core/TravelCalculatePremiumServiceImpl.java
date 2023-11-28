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
    private final DateTimeService dateTimeService;
    private final TravelCalculatePremiumRequestValidator validator;

    @Autowired
    public TravelCalculatePremiumServiceImpl(TravelCalculatePremiumRequestValidator validator, DateTimeService dateTimeService){
        this.dateTimeService = dateTimeService;
        this.validator = validator;
    }
    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> list = validator.validate(request);
        return buildResponse(request, list);
    }

    public TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request, List<ValidationError> list){
        if(!list.isEmpty()) {
            return new TravelCalculatePremiumResponse(list);
        }

        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setPersonFirstName(request.getPersonFirstName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setPersonLastName(request.getPersonLastName());
        long daysBetween = dateTimeService.getDaysBetween(request.getAgreementDateTo(), request.getAgreementDateFrom());
        response.setAgreementPrice(BigDecimal.valueOf(daysBetween));

        return response;
    }
}
