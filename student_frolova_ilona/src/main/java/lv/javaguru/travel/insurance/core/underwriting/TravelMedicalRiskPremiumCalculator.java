package lv.javaguru.travel.insurance.core.underwriting;

import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final DateTimeUtil dateTimeUtil;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return dateTimeUtil.getDifferenceInDays(request.getAgreementDateFrom(), request.getAgreementDateTo());
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
