package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.TravelCostCoefficient;
import lv.javaguru.travel.insurance.core.repositories.TravelCostCoefficientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelCostCoefficientCalculator {
    @Autowired
    private TravelCostCoefficientRepository  travelCostCoefficientRepository;
    BigDecimal calculate(AgreementDTO agreementDTO) {
       return travelCostCoefficientRepository.findByCost(agreementDTO.getTravelCost())
                .orElseThrow(()->new RuntimeException(
                        "cost of travel "+agreementDTO.getTravelCost()+" not supports by the system"))
                .getCoefficient();
    }

}
