package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public class TravelCalculateUnderwriting {
    private DateTimeService calculateDate = new DateTimeService();
    public BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request){
        return calculateDate.calculateDiffBetweenDays(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}
