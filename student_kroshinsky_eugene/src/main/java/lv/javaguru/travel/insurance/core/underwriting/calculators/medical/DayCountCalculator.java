package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.utils.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
class DayCountCalculator {
    @Autowired private DateTimeUtil dateTimeUtil;
    public BigDecimal calculate(TravelCalculatePremiumRequestV1 request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();
        return dateTimeUtil.calculateDateDifference(dateFrom, dateTo);
    }
}
