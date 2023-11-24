package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelPremiumCalculatorResult;
import lv.javaguru.travel.insurance.dto.TravelRisk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {
    @Autowired
    private List<TravelRiskPremiumCalculator> riskPremiumCalculators;
    @Override
    public TravelPremiumCalculatorResult calculatePremium(TravelCalculatePremiumRequest request){
        List<TravelRisk> travelRisks= riskPremiumCalculators.stream()
                .filter(calculator->request.getSelected_risks().contains(calculator.getRiskIc()))
                .map(calculator->new TravelRisk(calculator.getRiskIc(),calculator.calculatePremium(request)))
                .toList();
        BigDecimal totalPremium = travelRisks.stream()
                .map(TravelRisk::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new TravelPremiumCalculatorResult(totalPremium,travelRisks);
    }

}
