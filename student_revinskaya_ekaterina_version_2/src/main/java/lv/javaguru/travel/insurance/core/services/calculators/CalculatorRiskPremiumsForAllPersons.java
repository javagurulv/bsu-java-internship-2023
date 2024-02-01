package lv.javaguru.travel.insurance.core.services.calculators;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumCalculationResult;
import lv.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public
class CalculatorRiskPremiumsForAllPersons{
    @Autowired
    private TravelPremiumUnderwriting premiumUnderwriting;

    public void calculate(AgreementDTO agreement) {
        agreement.getPersons().forEach(person -> {
            List<RiskDTO> riskDTOS = premiumUnderwriting.calculatePremium(agreement, person).getRisks();
            person.setRisks(riskDTOS);
        });
    }

}
