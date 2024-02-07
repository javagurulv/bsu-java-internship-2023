package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MedicalRiskLimitLevelCalculatorTest {

    @Mock
    MedicalRiskLimitLevelRepository repository;

    @InjectMocks
    MedicalRiskLimitLevelCalculator calculator;

    @Mock
    private TravelCalculatePremiumRequest request;

    @Test
    public void shouldCalculateLimitLevelCoefficientCorrectly() {
        MedicalRiskLimitLevel limitLevel = mock(MedicalRiskLimitLevel.class);
        when(limitLevel.getCoefficient()).thenReturn(BigDecimal.valueOf(1.5));
        when(repository.findByMedicalRiskLimitLevelIc(any())).thenReturn(Optional.of(limitLevel));

        when(request.getMedicalRiskLimitLevel()).thenReturn("LEVEL_20000");

        ReflectionTestUtils.setField(calculator, "medicalRiskLimitLevelEnabled", true);

        assertEquals(BigDecimal.valueOf(1.5), calculator.calculate(request));
    }

    @Test
    public void shouldCalculateLimitLevelDefaultCoefficientCorrectly() {
        ReflectionTestUtils.setField(calculator, "medicalRiskLimitLevelEnabled", false);
        assertEquals(BigDecimal.ONE, calculator.calculate(request));
    }
}
