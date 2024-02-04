package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DayCountCalculator {

    private final DateTimeUtil dateTimeUtil;

    public BigDecimal calculate(TravelCalculatePremiumRequest request) {
        return BigDecimal.valueOf(
                dateTimeUtil.getDifferenceInDays(
                    request.getAgreementDateFrom(),
                    request.getAgreementDateTo()
                )
        );
    }
}
