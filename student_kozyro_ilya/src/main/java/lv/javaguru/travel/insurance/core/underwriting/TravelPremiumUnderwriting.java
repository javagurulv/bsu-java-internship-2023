package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.services.DateService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelPremiumUnderwriting  {

    @Autowired
    private DateService dateService;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return dateService.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}
