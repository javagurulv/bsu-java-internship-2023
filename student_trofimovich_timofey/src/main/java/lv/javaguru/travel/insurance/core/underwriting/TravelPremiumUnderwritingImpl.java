package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting{

    @Autowired
    SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;


    public TravelPremiumCalculationResult calculatePremium(TravelCalculatePremiumRequestV1 request) {
        List<RiskPremium> riskPremiums = selectedRisksPremiumCalculator.calculatePremiumForAllRisks(request);
        BigDecimal totalPremium = calculateTotalPremium(riskPremiums);
        return new TravelPremiumCalculationResult(totalPremium, riskPremiums);
    }

    private BigDecimal calculateTotalPremium(List<RiskPremium> riskPremiums) {
        return calculateSumOfRisksPremiums(riskPremiums);
    }
    private BigDecimal calculateSumOfRisksPremiums(List<RiskPremium> riskPremiums) {
        return riskPremiums.stream().map(RiskPremium::getPremium).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
