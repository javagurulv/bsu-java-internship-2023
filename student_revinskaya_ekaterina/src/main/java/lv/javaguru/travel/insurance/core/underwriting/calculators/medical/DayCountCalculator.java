package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class DayCountCalculator {
    @Autowired
    private DateTimeUtil dateTimeUtil;

    BigDecimal calculate(TravelCalculatePremiumRequest request){
       return dateTimeUtil.calculateDiffBetweenDays(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}
