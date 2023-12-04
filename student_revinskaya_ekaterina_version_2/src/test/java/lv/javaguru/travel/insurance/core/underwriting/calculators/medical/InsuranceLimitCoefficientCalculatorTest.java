package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InsuranceLimitCoefficientCalculatorTest {
    @InjectMocks
    private InsuranceLimitCoefficientCalculator insuranceLimitCoefficientCalculator;
    @Mock
    private MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;

    @Mock
    private PersonDTO personDTO;

    @Test
    public void calculateInsuranceLimitCoefficientTest() {
        when(personDTO.getMedicalRiskLimitLevel()).thenReturn("LEVEL_10000");
        MedicalRiskLimitLevel medicalRiskLimitLevel = mock(MedicalRiskLimitLevel.class);
        when(medicalRiskLimitLevel.getCoefficient()).thenReturn(BigDecimal.valueOf(1.0));
        when(medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc("LEVEL_10000"))
                .thenReturn(Optional.of(medicalRiskLimitLevel));
        ReflectionTestUtils.setField(insuranceLimitCoefficientCalculator, "medicalRiskLimitLevelEnabled", true);
        assertEquals(insuranceLimitCoefficientCalculator.calculate(personDTO), BigDecimal.valueOf(1.0));
    }

    @Test
    public void shouldReturn1Test() {
        ReflectionTestUtils.setField(insuranceLimitCoefficientCalculator, "medicalRiskLimitLevelEnabled", false);
        assertEquals(insuranceLimitCoefficientCalculator.calculate(personDTO), BigDecimal.valueOf(1));
    }

    @Test
    public void throwExceptionCountryDefaultDayRateTest() {
        when(personDTO.getMedicalRiskLimitLevel()).thenReturn("FAKE_LEVEL");
        when(medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc("FAKE_LEVEL"))
                .thenReturn(Optional.empty());
        ReflectionTestUtils.setField(insuranceLimitCoefficientCalculator, "medicalRiskLimitLevelEnabled", true);
        Throwable thrown = assertThrows(RuntimeException.class,
                () -> insuranceLimitCoefficientCalculator.calculate(personDTO));
        assertEquals(thrown.getMessage(), "medicalRiskLimitLevel with ic FAKE_LEVEL not found");
    }
}
