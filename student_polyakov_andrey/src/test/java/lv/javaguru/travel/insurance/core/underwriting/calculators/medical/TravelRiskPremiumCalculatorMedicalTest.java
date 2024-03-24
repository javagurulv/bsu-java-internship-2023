package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelRiskPremiumCalculatorMedicalTest {
    @Mock
    private Calculate_MEDICAL_CalculateDayCount medical_calculateDayCount;
    @Mock
    private Calculate_MEDICAL_FindAgeCoefficient medical_FindAgeCoefficient;
    @Mock
    private Calculate_MEDICAL_FindCountryDefaultDayRate medical_findCountryDefaultDayRate;
    @InjectMocks
    private TravelRiskPremiumCalculator_MEDICAL calculator;
    private TravelCalculatePremiumRequest request;
    @BeforeEach
    void setUp() {
        request = new TravelCalculatePremiumRequest();
    }
    @Test
    void shouldCalculatePremiumCorrectly() {
        BigDecimal daysCount = BigDecimal.valueOf(10);
        BigDecimal countryDefaultRate = BigDecimal.valueOf(20);
        BigDecimal ageCoefficient = BigDecimal.valueOf(1.2);

        when(medical_calculateDayCount.calculateDayCount(request)).thenReturn(daysCount);
        when(medical_FindAgeCoefficient.findAgeCoefficient(request)).thenReturn(ageCoefficient);
        when(medical_findCountryDefaultDayRate.findCountryDefaultDayRate(request)).thenReturn(countryDefaultRate);

        BigDecimal expectedPremium = countryDefaultRate.multiply(daysCount).multiply(ageCoefficient).setScale(2, RoundingMode.HALF_UP);
        BigDecimal result = calculator.calculatePremium(request);

        assertEquals(expectedPremium, result);
    }
}
