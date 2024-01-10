package lv.javaguru.travel.insurance.core.underwriting;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.risk.RiskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting{

    @Autowired
    private SelectedRisksPremiumCalculator selectedRisksPremiumCalculator;


    public TravelPremiumCalculationResult calculatePremium(AgreementDTO agreement, PersonDTO person) {
        List<RiskDTO> riskPremiums = selectedRisksPremiumCalculator.calculatePremiumForAllRisks(agreement, person);
        BigDecimal totalPremium = calculateTotalPremium(riskPremiums);
        return new TravelPremiumCalculationResult(totalPremium, riskPremiums);
    }

    private BigDecimal calculateTotalPremium(List<RiskDTO> riskPremiums) {
        return calculateSumOfRisksPremiums(riskPremiums);
    }
    private BigDecimal calculateSumOfRisksPremiums(List<RiskDTO> riskPremiums) {
        return riskPremiums.stream().map(RiskDTO::getPremium).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
