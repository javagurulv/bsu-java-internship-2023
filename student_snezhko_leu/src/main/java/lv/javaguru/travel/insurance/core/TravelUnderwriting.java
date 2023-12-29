package lv.javaguru.travel.insurance.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.math.BigDecimal;
import java.util.Date;

import static lv.javaguru.travel.insurance.rest.DateService.findDiffBetweenTwoDate;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class TravelUnderwriting {
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
