package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCancellationRiskPremiumCalculatorTest {

    @Mock private TCTravelCostCoefficientCalculator travelCostCoefficientCalculator;
    @Mock private TCAgeCoefficientCalculator ageCoefficientCalculator;
    @Mock private TCCountrySafetyRatingCoefficientCalculator countrySafetyRatingCoefficientCalculator;

    @InjectMocks
    private TravelCancellationRiskPremiumCalculator calculator;

    private AgreementDTO agreement;
    private PersonDTO person;

    @BeforeEach
    void setUp() {
        agreement = new AgreementDTO();
        person = new PersonDTO();
    }

    @Test
    void shouldCalculatePremiumCorrectly() {
        BigDecimal travelCostCoefficient = BigDecimal.valueOf(10);
        BigDecimal ageCoefficient = BigDecimal.valueOf(20);
        BigDecimal countrySafetyRatingCoefficient = BigDecimal.valueOf(1.2);

        when(travelCostCoefficientCalculator.calculate(person)).thenReturn(travelCostCoefficient);
        when(ageCoefficientCalculator.calculate(person)).thenReturn(ageCoefficient);
        when(countrySafetyRatingCoefficientCalculator.calculate(agreement)).thenReturn(countrySafetyRatingCoefficient);

        BigDecimal expectedPremium = travelCostCoefficient
                .add(ageCoefficient)
                .add(countrySafetyRatingCoefficient)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal result = calculator.calculatePremium(agreement, person);

        assertEquals(expectedPremium, result);
    }

}