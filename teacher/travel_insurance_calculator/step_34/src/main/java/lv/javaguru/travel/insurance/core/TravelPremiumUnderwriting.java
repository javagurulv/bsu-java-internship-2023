package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelPremiumUnderwriting {

    @Autowired private DateTimeService dateTimeService;

    BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        var daysBetween = dateTimeService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());
        return new BigDecimal(daysBetween);
    }

}
