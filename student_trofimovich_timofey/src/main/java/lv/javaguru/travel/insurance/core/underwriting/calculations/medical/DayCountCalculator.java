package lv.javaguru.travel.insurance.core.underwriting.calculations.medical;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class DayCountCalculator {
    @Autowired
    private DateTimeUtil dateTimeUtil;
    long getNumberOfDays(TravelCalculatePremiumRequest request) {
        return dateTimeUtil.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }
}
