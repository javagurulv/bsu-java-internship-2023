package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.TravelCostCoefficient;
import lv.javaguru.travel.insurance.core.repositories.TravelCostCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class TravelCostCoefficientCalculator {

    @Autowired
    private TravelCostCoefficientRepository repository;

    BigDecimal getCostCoefficient(AgreementDTO agreement) {
        Optional<TravelCostCoefficient> coefficient = repository.findCoefficient(agreement.getAgreementPremium());
        return coefficient.map(TravelCostCoefficient::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Travel cost coefficient calculator not found for cost: " + agreement.getAgreementPremium()));
    }

}
