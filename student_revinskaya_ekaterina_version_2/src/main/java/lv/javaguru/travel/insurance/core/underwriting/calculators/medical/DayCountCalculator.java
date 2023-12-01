package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class DayCountCalculator {
    @Autowired
    private DateTimeUtil dateTimeUtil;

    BigDecimal calculate(AgreementDTO agreement){
       return dateTimeUtil.calculateDiffBetweenDays(agreement.getAgreementDateFrom(), agreement.getAgreementDateTo());
    }
}
