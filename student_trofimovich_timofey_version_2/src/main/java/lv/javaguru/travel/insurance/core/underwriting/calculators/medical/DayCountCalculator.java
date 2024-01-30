package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.springframework.stereotype.Component;

@Component
public class DayCountCalculator {
     long getNumberOfDays(AgreementDTO agreement) {
        return DateTimeUtil.getDaysBetween(agreement.getAgreementDateFrom(), agreement.getAgreementDateTo());
    }
}
