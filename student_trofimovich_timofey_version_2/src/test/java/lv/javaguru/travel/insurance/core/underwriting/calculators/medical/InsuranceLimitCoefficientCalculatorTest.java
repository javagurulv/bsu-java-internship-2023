package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.junit.jupiter.api.BeforeEach;
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
    private MedicalRiskLimitLevelRepository repository;
    @InjectMocks
    private InsuranceLimitCoefficientCalculator calculator;
    private AgreementDTO agreement;

    @BeforeEach
    void init() {
        agreement = mock(AgreementDTO.class);
    }

    @Test
    void shouldReturnDefaultCoefficientWhenLimitLevelIsNotEnabled() {
        ReflectionTestUtils.setField(calculator, "limitLevelIsEnabled", false);
        BigDecimal insuranceLimitCoefficient = calculator.getInsuranceLimitCoefficient(agreement);
        assertThat(insuranceLimitCoefficient).isEqualTo(BigDecimal.ONE);
        verifyNoInteractions(repository);
    }

    @Test
    void shouldReturnCoefficientWhenLimitLevelIcIsCorrect() {
        ReflectionTestUtils.setField(calculator, "limitLevelIsEnabled", true);
        BigDecimal expectedCoefficient = BigDecimal.valueOf(2.0);
        MedicalRiskLimitLevel medicalRiskLimitLevel = mock(MedicalRiskLimitLevel.class);
        when(medicalRiskLimitLevel.getCoefficient()).thenReturn(expectedCoefficient);
        when(repository.findByMedicalRiskLimitLevelIc(agreement.getMedicalRiskLimitLevel()))
                .thenReturn(Optional.of(medicalRiskLimitLevel));
        BigDecimal result = calculator.getInsuranceLimitCoefficient(agreement);
        assertEquals(expectedCoefficient, result);
    }

    @Test
    void shouldThrowExceptionWhenCountryDayRateNotFound() {
        ReflectionTestUtils.setField(calculator, "limitLevelIsEnabled", true);
        when(repository.findByMedicalRiskLimitLevelIc(agreement.getCountry())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> calculator.getInsuranceLimitCoefficient(agreement));
        assertEquals("Insurance limit level coefficient not found for limit level ic: " + agreement.getMedicalRiskLimitLevel(), exception.getMessage());
    }
}
