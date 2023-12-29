package lv.javaguru.travel.insurance.core.underwriting.calculators.cancel;

import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.domain.TravelCostCoefficient;
import lv.javaguru.travel.insurance.core.repositories.TravelCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class TravelCostCalculator {

    @Autowired private TravelCostRepository travelCostRepository;

    BigDecimal calculate(PersonDto person) {
        return travelCostRepository.findCoefficient(person.getTravelCost())
                .map(TravelCostCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Travel Cost coefficient not found for travel cost = " + person.getTravelCost()));
    }

}
