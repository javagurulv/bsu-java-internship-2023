package lv.javaguru.travel.insurance.core.underwriting.calculators;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation.TravelRiskPremiumCalculatorCancellationComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
class TravelRiskPremiumCalculatorCancellation implements TravelRiskPremiumCalculator {
    @Autowired
    private List<TravelRiskPremiumCalculatorCancellationComponent> components;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {

        return components.stream()
                .map(
                        component ->
                                component.calculatePremium(agreement, person)
                )
                .reduce(BigDecimal::add)
                .get();
    }

    @Override
    public String getIc() {
        return "TRAVEL_CANCELLATION";
    }
}
