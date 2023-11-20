package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelPremiumUnderwriting {
    @Autowired private DateDifferenceService dateDifferenceService;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request){
        return dateDifferenceService.calculateDateDifference(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}
