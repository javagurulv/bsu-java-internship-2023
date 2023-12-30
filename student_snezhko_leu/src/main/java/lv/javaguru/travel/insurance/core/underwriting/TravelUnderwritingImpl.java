package lv.javaguru.travel.insurance.core.underwriting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static lv.javaguru.travel.insurance.core.util.DateTimeUtil.findDiffBetweenTwoDate;

@Getter
@Setter
@NoArgsConstructor
@Component
//@AllArgsConstructor
class TravelUnderwritingImpl implements TravelUnderwriting{
    public BigDecimal calculatePremium(TravelCalculatePremiumResponse response) {
        /*return BigDecimal.valueOf(
                response.getRisks().stream()
                        .mapToLong(risk -> risk.getCost())
                        .sum()
        );
*/

        return findDiffBetweenTwoDate(response.getAgreementDateTo(), response.getAgreementDateFrom());
    }
}
