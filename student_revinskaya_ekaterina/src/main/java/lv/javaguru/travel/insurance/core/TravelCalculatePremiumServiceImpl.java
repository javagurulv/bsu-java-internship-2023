package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {
    private BigDecimal calculateDiffBetweenDays(Date date1, Date date2){
        return BigDecimal.valueOf(date1.getTime() - date2.getTime());
    }

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setAgreementDateTo(request.getAgreementDateTo());

        response.setAgreementPrice(calculateDiffBetweenDays(response.getAgreementDateFrom(), response.getAgreementDateTo()));
        return response;
    }

}
