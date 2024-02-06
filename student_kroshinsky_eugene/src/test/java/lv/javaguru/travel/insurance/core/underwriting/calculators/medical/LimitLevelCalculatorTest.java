package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import lv.javaguru.travel.insurance.core.domain.LimitLevel;
import lv.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import lv.javaguru.travel.insurance.core.repositories.LimitLevelRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class LimitLevelCalculatorTest {
    @Mock private TravelCalculatePremiumRequest request;
    @Mock private LimitLevelRepository repository;
    @Mock private LimitLevel limitLevel;
    @InjectMocks private LimitLevelCalculator calculator;
    @Test
    void injectedObjectsAreNotNull() {
        assertNotNull(calculator);
        assertNotNull(request);
        assertNotNull(repository);
        assertNotNull(limitLevel);
    }
    @Test
    void calculateTest() {
        when(request.getMedicalRiskLimitLevel()).thenReturn("limit");
        when(repository.findByIc(anyString())).thenReturn(Optional.of(limitLevel));
        BigDecimal expectedResult = BigDecimal.valueOf(10);
        when(limitLevel.getCoefficient()).thenReturn(expectedResult);

        BigDecimal result = calculator.calculate(request);
        assertEquals(expectedResult, result);
    }
}