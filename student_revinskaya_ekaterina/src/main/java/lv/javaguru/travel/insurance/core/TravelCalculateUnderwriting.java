package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class TravelCalculateUnderwriting {

    private DateTimeService calculateDate = new DateTimeService();
    public BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request){
        return calculateDate.calculateDiffBetweenDays(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}
