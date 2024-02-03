package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelMedicalCalculatorTest {
    @Mock
    private AgeCoefficientCalculator ageCoefficientCalculator;
    @Mock private DayCountCalculator dayCountCalculator;
    @Mock private CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    @Mock private TravelCalculatePremiumRequest request;
    @InjectMocks private TravelMedicalCalculator travelMedicalCalculator;
    @Test
    void injectedRepositoryAreNotNull() {
        assertNotNull(ageCoefficientCalculator);
        assertNotNull(request);
        assertNotNull(dayCountCalculator);
        assertNotNull(travelMedicalCalculator);
        assertNotNull(countryDefaultDayRateCalculator);
    }
    @Test
    void calculatePremiumTest1() {
        when(ageCoefficientCalculator.calculate(request)).thenReturn(BigDecimal.valueOf(10));
        when(dayCountCalculator.calculate(request)).thenReturn(BigDecimal.valueOf(5));
        when(countryDefaultDayRateCalculator.calculate(request)).thenReturn(BigDecimal.valueOf(4));

        BigDecimal result = travelMedicalCalculator.calculatePremium(request);
        assertEquals(new BigDecimal(200), result);
    }
}