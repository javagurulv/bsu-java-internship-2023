package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class DayCountCalculator {
    @Autowired
    private DateTimeUtil dateTimeUtil;

    BigDecimal calculate(AgreementDTO agreement){
       return dateTimeUtil
               .calculateDiffBetweenDays(agreement.getAgreementDateFrom(),
                       agreement.getAgreementDateTo())
               .setScale(2, RoundingMode.HALF_UP);

    }
}
