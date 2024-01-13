package lv.javaguru.travel.insurance.core.underwriting.calculators.cancel;

import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.domain.TCTravelCostCoefficient;
import lv.javaguru.travel.insurance.core.repositories.TCTravelCostCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TCTravelCostCoefficientCalculator {

    @Autowired private TCTravelCostCoefficientRepository TCTravelCostCoefficientRepository;

    BigDecimal calculate(PersonDto person) {
        return TCTravelCostCoefficientRepository.findCoefficient(person.getTravelCost())
                .map(TCTravelCostCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Travel Cost coefficient not found for travel cost = " + person.getTravelCost()));
    }

}
