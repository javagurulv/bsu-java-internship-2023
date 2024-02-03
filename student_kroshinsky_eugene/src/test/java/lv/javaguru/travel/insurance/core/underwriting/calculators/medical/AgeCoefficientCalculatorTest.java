package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.AgeCoefficient;
import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.repositories.AgeCoefficientRepository;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.utils.AgeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgeCoefficientCalculatorTest {
    @Mock private TravelCalculatePremiumRequest request;
    @Mock private AgeUtil ageUtil;
    @Mock private AgeCoefficientRepository repository;
    @Mock private AgeCoefficient ageCoefficient;
    @InjectMocks private AgeCoefficientCalculator calculator;
    @Test
    void injectedObjectsAreNotNull() {
        assertNotNull(ageUtil);
        assertNotNull(calculator);
        assertNotNull(request);
        assertNotNull(repository);
        assertNotNull(ageCoefficient);
    }
    @Test
    void calculateTest() {
        when(ageUtil.calculateAge(request)).thenReturn(10);
        when(repository.findByAge(anyInt())).thenReturn(Optional.of(ageCoefficient));
        BigDecimal expectedResult = BigDecimal.valueOf(1);
        when(ageCoefficient.getCoefficient()).thenReturn(expectedResult);

        BigDecimal result = calculator.calculate(request);
        assertEquals(expectedResult, result);
    }
}