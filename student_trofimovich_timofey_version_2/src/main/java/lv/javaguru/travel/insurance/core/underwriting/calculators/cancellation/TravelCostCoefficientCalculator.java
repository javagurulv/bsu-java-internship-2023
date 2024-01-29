package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.TCTravelCostCoefficient;
import lv.javaguru.travel.insurance.core.repositories.TCTravelCostCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class TravelCostCoefficientCalculator {

    @Autowired
    private TCTravelCostCoefficientRepository repository;

    BigDecimal getCostCoefficient(PersonDTO personDTO) {

        return repository.findCoefficient(personDTO.getTravelCost()).map(TCTravelCostCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Travel cost coefficient calculator not found for cost: " + personDTO.getTravelCost()));
    }

}
