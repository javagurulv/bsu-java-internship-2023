package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.TCTravelCostCoefficient;
import lv.javaguru.travel.insurance.core.repositories.TCTravelCostCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class TravelCostCoefficientCalculator {

    @Autowired
    private TCTravelCostCoefficientRepository repository;

    BigDecimal getCostCoefficient(AgreementDTO agreement) {
        Optional<TCTravelCostCoefficient> coefficient = repository.findCoefficient(agreement.getAgreementPremium());
        return coefficient.map(TCTravelCostCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Travel cost coefficient calculator not found for cost: " + agreement.getAgreementPremium()));
    }

}
