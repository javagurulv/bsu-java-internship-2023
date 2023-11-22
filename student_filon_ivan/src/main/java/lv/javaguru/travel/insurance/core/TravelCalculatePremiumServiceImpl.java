package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {
    @Autowired private DateTimeService dateTimeService = new DateTimeService();
    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
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
