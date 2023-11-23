package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = new TravelCalculatePremiumResponse();

        Date from = request.getAgreementDateFrom();
        Date to = request.getAgreementDateTo();

        travelCalculatePremiumResponse.setAgreementDateFrom(from);
        travelCalculatePremiumResponse.setAgreementDateTo(to);
        travelCalculatePremiumResponse.setPersonFirstName(request.getPersonFirstName());
        travelCalculatePremiumResponse.setPersonLastName(request.getPersonLastName());
        travelCalculatePremiumResponse.setAgreementPrice(
                new BigDecimal(
                        TimeUnit.DAYS.convert(to.getTime() - from.getTime(), TimeUnit.MILLISECONDS)
                )
        );

        return travelCalculatePremiumResponse;
    }

}
