package lv.javaguru.travel.insurance.core.underwriting.calculations.medical;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
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
    AgeCoefficientCalculator ageCoefficientCalculator;
    @Mock
    CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    @Mock
    DayCountCalculator dayCountCalculator;
    @Mock InsuranceLimitCoefficientCalculator limitCoefficientCalculator;
    @InjectMocks TravelMedicalPremiumCalculation medicalPremiumCalculation;

    @Test
    public void shouldReturnCorrectPremium() {
        long numberOfDays = 12L;
        BigDecimal countryRate = new BigDecimal("1.3");
        BigDecimal ageCoefficient = new BigDecimal("1.6");
        BigDecimal limitLevelCoefficient = new BigDecimal("2.0");
        TravelCalculatePremiumRequestV1 request = mock(TravelCalculatePremiumRequestV1.class);
        when(dayCountCalculator.getNumberOfDays(request)).thenReturn(numberOfDays);
        when(countryDefaultDayRateCalculator.getCountryDefaultDayRate(request)).thenReturn(countryRate);
        when(ageCoefficientCalculator.getAgeCoefficient(request)).thenReturn(ageCoefficient);
        when(limitCoefficientCalculator.getInsuranceLimitCoefficient(request)).thenReturn(limitLevelCoefficient);
        BigDecimal calculatedPremium = medicalPremiumCalculation.calculatePremium(request);
        BigDecimal expectedPremium = countryRate
                .multiply(ageCoefficient)
                .multiply(BigDecimal.valueOf(numberOfDays))
                .multiply(limitLevelCoefficient);
        assertThat(calculatedPremium.stripTrailingZeros()).isEqualTo(expectedPremium.stripTrailingZeros());
    }




}
