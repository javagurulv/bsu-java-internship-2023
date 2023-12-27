package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.TravelPremiumCalculatorResult;
import lv.javaguru.travel.insurance.dto.TravelRisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {
    @Autowired
    private SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;
    @Override
    public TravelPremiumCalculatorResult calculatePremium(TravelCalculatePremiumRequestV1 request){
        List<TravelRisk> travelRisks= selectedRisksPremiumCalculator.calculateSelectedRisksPremium(request);
        BigDecimal totalPremium = travelRisks.stream()
                .map(TravelRisk::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new TravelPremiumCalculatorResult(totalPremium,travelRisks);
    }

}
