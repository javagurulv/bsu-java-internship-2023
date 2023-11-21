package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class TravelCalculateUnderwriting {

    private DateTimeUtil calculateDate = new DateTimeUtil();
    public BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request){
        return calculateDate.calculateDiffBetweenDays(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}
