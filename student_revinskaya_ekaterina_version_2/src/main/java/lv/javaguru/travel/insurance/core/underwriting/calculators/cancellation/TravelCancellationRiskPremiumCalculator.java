package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.underwriting.TravelRiskPremiumCalculator;
import lv.javaguru.travel.insurance.core.underwriting.calculators.medical.TMAgeCoefficientCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class TravelCancellationRiskPremiumCalculator implements TravelRiskPremiumCalculator {
    @Autowired
    private TCAgeCoefficientCalculator TCAgeCoefficientCalculator;
    @Autowired
    private TravelCostCoefficientCalculator travelCostCoefficientCalculator;
    @Autowired
    private CountrySafetyRatingCoefficientCalculator countrySafetyRatingCoefficientCalculator;
    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        BigDecimal ageCoefficient = TCAgeCoefficientCalculator.calculate(person);
        BigDecimal travelCostCoefficient = travelCostCoefficientCalculator.calculate(agreement);
        BigDecimal countrySafetyRatingCoefficient = countrySafetyRatingCoefficientCalculator.calculate(agreement);
        return ageCoefficient.add(travelCostCoefficient)
                .add(countrySafetyRatingCoefficient)
                .setScale(2, RoundingMode.HALF_UP)
                .stripTrailingZeros();
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_CANCELLATION";
    }
}
