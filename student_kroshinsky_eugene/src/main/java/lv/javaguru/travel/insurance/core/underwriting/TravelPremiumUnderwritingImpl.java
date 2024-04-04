package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.underwriting.calculators.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {
    @Autowired private List<TravelRiskPremiumCalculator> calculators;
    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request){
        return calculators.stream()
                .filter(c -> request.getSelectedRisks().contains(c.getRiskIc()))
                .map(c -> c.calculatePremium(request))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
