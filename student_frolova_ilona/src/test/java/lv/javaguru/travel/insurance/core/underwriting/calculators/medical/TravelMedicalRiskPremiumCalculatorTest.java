package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelMedicalRiskPremiumCalculatorTest {

    @Mock private DayCountCalculator dayCountCalculator;
    @Mock private CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;

    @Mock private AgeCoefficientCalculator ageCoefficientCalculator;

    @InjectMocks private TravelMedicalRiskPremiumCalculator calculator;

    @Test
    public void shouldCalculatePremium() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(dayCountCalculator.calculate(any())).thenReturn(BigDecimal.valueOf(2));
        when(countryDefaultDayRateCalculator.calculate(any())).thenReturn(BigDecimal.TEN);
        when(ageCoefficientCalculator.calculate(any())).thenReturn(BigDecimal.valueOf(1.20));

        BigDecimal premium = calculator.calculatePremium(request);
        assertEquals(premium.stripTrailingZeros(),
                new BigDecimal("24").stripTrailingZeros());
    }

}
