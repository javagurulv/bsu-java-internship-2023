package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.repositories.calculate.cancellation.TravelCostCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class TravelCalculateCostCoefficientCancellation implements TravelRiskPremiumCalculatorCancellationComponent{
    @Autowired
    private TravelCostCoefficientRepository travelCostCoefficientRepository;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        return travelCostCoefficientRepository.findCoefficientByCost(agreement.getCost())
                .get().getCoefficient();
    }
}
