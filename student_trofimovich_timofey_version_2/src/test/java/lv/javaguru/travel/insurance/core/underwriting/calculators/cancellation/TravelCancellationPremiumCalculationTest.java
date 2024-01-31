package lv.javaguru.travel.insurance.core.underwriting.calculators.cancellation;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelCancellationPremiumCalculationTest {

    @Mock
    private TravelCostCoefficientCalculator costCoefficientCalculator;
    @Mock
    private TCCountrySafeRatingCoefficientCalculator safeRatingCoefficientCalculator;
    @Mock
    private TripCancellationAgeCoefficientCalculator tripCancellationAgeCoefficientCalculator;
    @InjectMocks
    private TravelCancellationPremiumCalculation calculation;
    @Test
    void shouldCalculatePremium() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(costCoefficientCalculator.getCostCoefficient(person)).thenReturn(BigDecimal.ONE);
        when(safeRatingCoefficientCalculator.getCountrySafeRatingCoefficient(agreement)).thenReturn(BigDecimal.ONE);
        when(tripCancellationAgeCoefficientCalculator.calculateAgeCoefficient(person)).thenReturn(BigDecimal.ONE);
        BigDecimal calculatedPremium = calculation.calculatePremium(agreement, person);
        assertThat(calculatedPremium.compareTo(new BigDecimal("3"))).isEqualTo(0);
    }

}
