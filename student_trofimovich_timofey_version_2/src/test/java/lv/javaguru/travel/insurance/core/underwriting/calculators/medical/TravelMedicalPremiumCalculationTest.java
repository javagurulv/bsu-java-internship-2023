package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

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
public class TravelMedicalPremiumCalculationTest {

    @Mock
    private AgeCoefficientCalculator ageCoefficientCalculator;
    @Mock
    private CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    @Mock
    private DayCountCalculator dayCountCalculator;
    @Mock
    private InsuranceLimitCoefficientCalculator limitCoefficientCalculator;
    @InjectMocks
    private TravelMedicalPremiumCalculation medicalPremiumCalculation;

    @Test
    public void shouldReturnCorrectPremium() {

        long numberOfDays = 12L;
        BigDecimal countryRate = new BigDecimal("1.3");
        BigDecimal ageCoefficient = new BigDecimal("1.6");
        BigDecimal limitLevelCoefficient = new BigDecimal("2.0");

        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);

        when(dayCountCalculator.getNumberOfDays(agreement)).thenReturn(numberOfDays);
        when(countryDefaultDayRateCalculator.getCountryDefaultDayRate(agreement)).thenReturn(countryRate);
        when(ageCoefficientCalculator.getAgeCoefficient(person)).thenReturn(ageCoefficient);
        when(limitCoefficientCalculator.getInsuranceLimitCoefficient(person)).thenReturn(limitLevelCoefficient);
        BigDecimal calculatedPremium = medicalPremiumCalculation.calculatePremium(agreement, person);
        BigDecimal expectedPremium = countryRate
                .multiply(ageCoefficient)
                .multiply(BigDecimal.valueOf(numberOfDays))
                .multiply(limitLevelCoefficient);
        assertThat(calculatedPremium.stripTrailingZeros()).isEqualTo(expectedPremium.stripTrailingZeros());
    }


}
