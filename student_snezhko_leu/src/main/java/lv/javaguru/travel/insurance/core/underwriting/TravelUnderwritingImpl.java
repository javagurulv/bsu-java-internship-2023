package lv.javaguru.travel.insurance.core.underwriting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Component
//@AllArgsConstructor
class TravelUnderwritingImpl implements TravelUnderwriting{
    //@Autowired
    //private List<TravelRiskPremiumCalculator> riskCalculators;

    @Autowired
    SelectedRisksPremiumCalculator calculator;
    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request) {

        BigDecimal result = BigDecimal.ZERO;
        List<TravelCalculatePremiumRisk> risks = calculator.calculatePremiumForAllRisks(request);

        for (TravelCalculatePremiumRisk risk : risks) {
            result = result.add(risk.getPremium());
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

    //@Override
    /*public List<TravelRiskPremiumCalculator> getRiskCalculators() {
        return riskCalculators;
    }*/
}
