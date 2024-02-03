package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.stereotype.Component;

import static lv.javaguru.travel.insurance.core.util.DateTimeUtil.findDiffBetweenTwoDate;

@Component
class TravelCalculateDayCount {
    public long calculatePremium(TravelCalculatePremiumRequestV1 request) {
        return findDiffBetweenTwoDate(request.getAgreementDateTo(), request.getAgreementDateFrom());
    }
}
