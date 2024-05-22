package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.repositories.calculate.cancellation.TCAgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.util.CalculateAgeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelAgeCoefficientCancellation implements TravelRiskPremiumCalculatorCancellationComponent{
    @Autowired
    private CalculateAgeUtil calculateAgeUtil;

    @Autowired
    private TCAgeCoefficientRepository ageCoefficientRepository;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        return ageCoefficientRepository
                .findCoefficient(
                calculateAgeUtil
                        .calculateAge(
                        person.getPersonBirthDate()
                        )
                )
                .get().getCoefficient();
    }
}
