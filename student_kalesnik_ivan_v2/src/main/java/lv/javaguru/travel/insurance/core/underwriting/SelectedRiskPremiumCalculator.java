package lv.javaguru.travel.insurance.core.underwriting;

import lombok.Setter;
import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.RiskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Setter
public class SelectedRiskPremiumCalculator {
    @Autowired
    private List<TravelRiskPremiumCalculator> riskPremiumCalculators;

    public List<RiskDto> calculatePremiumForAllRisks(AgreementDto request, PersonDto person) {
        return request.getSelectedRisks().stream()
                .map(riskIc -> new RiskDto(riskIc, calculatePremiumForRisk(riskIc, request, person)))
                .toList();
    }

    private BigDecimal calculatePremiumForRisk(String riskIc, AgreementDto request, PersonDto person) {
        return findRiskPremiumCalculator(riskIc).calculatePremium(request, person);
    }

    TravelRiskPremiumCalculator findRiskPremiumCalculator(String riskIc) {
        return riskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }
}
