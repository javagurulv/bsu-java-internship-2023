package lv.javaguru.travel.insurance.core.underwriting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static lv.javaguru.travel.insurance.core.util.DateTimeUtil.findDiffBetweenTwoDate;

@Getter
@Setter
@NoArgsConstructor
@Component
//@AllArgsConstructor
class TravelUnderwritingImpl implements TravelUnderwriting{
    @Autowired
    List<TravelRiskPremiumCalculator> riskCalculators;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        BigDecimal result = BigDecimal.ZERO;
        for (TravelRiskPremiumCalculator calc : riskCalculators) {
            if (request.getSelected_risks().contains(calc.getIc())) {
                result = result.add(calc.calculatePremium(request));
            }
        }

        return result;
        /*return BigDecimal.valueOf(
                response.getRisks().stream()
                        .mapToLong(risk -> risk.getCost())
                        .sum()
        );
*/

//        return findDiffBetweenTwoDate(response.getAgreementDateTo(), response.getAgreementDateFrom());

    }
}
