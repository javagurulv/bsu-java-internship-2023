package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.TravelCostCoefficient;
import lv.javaguru.travel.insurance.core.repositories.TravelCostCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelCostCoefficientCalculator {

    @Autowired private TravelCostCoefficientRepository travelCostCoefficientRepository;

    BigDecimal calculate(PersonDTO person) {
        return travelCostCoefficientRepository.findCoefficient(person.getTravelCost())
                .map(TravelCostCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Travel Cost coefficient not found for travel cost = " + person.getTravelCost()));
    }

}
