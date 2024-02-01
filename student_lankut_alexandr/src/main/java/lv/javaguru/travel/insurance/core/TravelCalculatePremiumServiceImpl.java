package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse premiumResponse = new TravelCalculatePremiumResponse();

        var dateTo = request.getAgreementDateTo();
        var dateFrom = request.getAgreementDateFrom();

        premiumResponse.setPersonFirstName(request.getPersonFirstName());
        premiumResponse.setAgreementDateFrom(dateFrom);
        premiumResponse.setAgreementDateTo(dateTo);
        premiumResponse.setPersonLastName(request.getPersonLastName());
        premiumResponse.setAgreementPrice(new BigDecimal(
                TimeUnit.DAYS.convert(dateTo.getTime() - dateFrom.getTime(), TimeUnit.MILLISECONDS)
        ));

        return premiumResponse;
    }

}
