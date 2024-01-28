package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TravelCancellationPremiumCalculation implements TravelRiskPremiumCalculator {

    @Autowired
    private TravelCostCoefficientCalculator costCoefficientCalculator;
    @Autowired
    private TCCountrySafeRatingCoefficientCalculator countrySafeRatingCoefficientCalculator;
    @Autowired
    private TripCancellationAgeCoefficientCalculator tripCancellationAgeCoefficientCalculator;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        return costCoefficientCalculator.getCostCoefficient(person)
                .add(countrySafeRatingCoefficientCalculator.getCountrySafeRatingCoefficient(agreement))
                .add(tripCancellationAgeCoefficientCalculator.calculateAgeCoefficient(person));
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_CANCELLATION";
    }
}
