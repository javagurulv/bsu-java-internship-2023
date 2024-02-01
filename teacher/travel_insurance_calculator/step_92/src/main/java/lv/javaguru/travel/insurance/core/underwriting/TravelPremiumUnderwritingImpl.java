package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.dto.RiskPremium;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {

    @Autowired private SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;

    @Override
    public TravelPremiumCalculationResult calculatePremium(TravelCalculatePremiumRequestV1 request) {
        List<RiskPremium> riskPremiums = calculateSelectedRisksPremium(request);
        BigDecimal totalPremium = calculateTotalPremium(riskPremiums);
        return new TravelPremiumCalculationResult(totalPremium, riskPremiums);
    }

    private List<RiskPremium> calculateSelectedRisksPremium(TravelCalculatePremiumRequestV1 request) {
        return selectedRisksPremiumCalculator.calculatePremiumForAllRisks(request);
    }

    private static BigDecimal calculateTotalPremium(List<RiskPremium> riskPremiums) {
        return riskPremiums.stream()
                .map(RiskPremium::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
