package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class TravelPremiumUnderwriting {
    @Autowired DateTimeService service;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request){
        long daysBetween = service.getDaysBetween(request.getAgreementDateTo(), request.getAgreementDateFrom());
        return new BigDecimal(daysBetween);
    }
}
