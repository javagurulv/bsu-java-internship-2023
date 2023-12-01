package lv.javaguru.travel.insurance.core.underwriting.calculations.medical;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelMedicalPremiumCalculatonTest {

    @Mock
    AgeCoefficientCalculator ageCoefficientCalculator;
    @Mock
    CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    @Mock
    DayCountCalculator dayCountCalculator;
    @InjectMocks TravelMedicalPremiumCalculation medicalPremiumCalculation;

    @Test
    public void shouldReturnCorrectPremium() {
        long numberOfDays = 12L;
        BigDecimal countryRate = new BigDecimal("1.3");
        BigDecimal ageCoefficient = new BigDecimal("1.6");
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(dayCountCalculator.getNumberOfDays(request)).thenReturn(numberOfDays);
        when(countryDefaultDayRateCalculator.getCountryDefaultDayRate(request)).thenReturn(countryRate);
        when(ageCoefficientCalculator.getAgeCoefficient(request)).thenReturn(ageCoefficient);
        BigDecimal calculatedPremium = medicalPremiumCalculation.calculatePremium(request);
        BigDecimal expectedPremium = countryRate.multiply(ageCoefficient).multiply(BigDecimal.valueOf(numberOfDays));
        assertThat(calculatedPremium.stripTrailingZeros()).isEqualTo(expectedPremium.stripTrailingZeros());
    }




}
