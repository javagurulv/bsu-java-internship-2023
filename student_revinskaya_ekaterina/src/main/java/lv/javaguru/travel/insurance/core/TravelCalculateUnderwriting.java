package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;
import java.util.Date;

public class TravelCalculateUnderwriting {
    private CalculatorDate calculateDate = new CalculatorDate();
    public BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request){
        return calculateDate.calculateDiffBetweenDays(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}
