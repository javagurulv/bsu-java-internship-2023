package lv.javaguru.travel.insurance.core.underwriting.calculations.medical;

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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InsuranceLimitCoefficientCalculatorTest {
    @Mock
    MedicalRiskLimitLevelRepository repository;
    @InjectMocks InsuranceLimitCoefficientCalculator calculator;

    @Test
    void shouldReturnDefaultCoefficientWhenLimitLevelIsNotEnabled() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        ReflectionTestUtils.setField(calculator, "limitLevelIsEnabled", false);
        BigDecimal insuranceLimitCoefficient = calculator.getInsuranceLimitCoefficient(request);
        assertThat(insuranceLimitCoefficient).isEqualTo(BigDecimal.ONE);
        verifyNoInteractions(repository);
    }
    @Test
    void shouldReturnCoefficientWhenLimitLevelIcIsCorrect() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        ReflectionTestUtils.setField(calculator, "limitLevelIsEnabled", true);
        BigDecimal expectedCoefficient = BigDecimal.valueOf(2.0);
        MedicalRiskLimitLevel medicalRiskLimitLevel = mock(MedicalRiskLimitLevel.class);
        when(medicalRiskLimitLevel.getCoefficient()).thenReturn(expectedCoefficient);
        when(repository.findByMedicalRiskLimitLevelIc(request.getMedicalRiskLimitLevel()))
                .thenReturn(Optional.of(medicalRiskLimitLevel));
        BigDecimal result = calculator.getInsuranceLimitCoefficient(request);
        assertEquals(expectedCoefficient, result);
    }

    @Test
    void shouldThrowExceptionWhenCountryDayRateNotFound() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        ReflectionTestUtils.setField(calculator, "limitLevelIsEnabled", true);
        when(repository.findByMedicalRiskLimitLevelIc(request.getCountry())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.getInsuranceLimitCoefficient(request));
        assertEquals("Insurance limit level coefficient not found for limit level ic: " + request.getMedicalRiskLimitLevel(), exception.getMessage());
    }
}
