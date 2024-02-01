package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class DayCountCalculator {
    @Autowired
    private DateTimeUtil dateTimeUtil;
    long getNumberOfDays(TravelCalculatePremiumRequestV1 request) {
        return dateTimeUtil.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}
