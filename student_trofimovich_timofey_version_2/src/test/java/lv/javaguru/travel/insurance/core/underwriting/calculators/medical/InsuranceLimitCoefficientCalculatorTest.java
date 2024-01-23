package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.TMMedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.TMMedicalRiskLimitLevelRepository;
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
    private TMMedicalRiskLimitLevelRepository repository;
    @InjectMocks
    private InsuranceLimitCoefficientCalculator calculator;
    private PersonDTO person;
    private AgreementDTO agreement;

    @BeforeEach
    void init() {
        person = mock(PersonDTO.class);
        agreement = mock(AgreementDTO.class);
    }

    @Test
    void shouldReturnDefaultCoefficientWhenLimitLevelIsNotEnabled() {
        ReflectionTestUtils.setField(calculator, "limitLevelIsEnabled", false);
        BigDecimal insuranceLimitCoefficient = calculator.getInsuranceLimitCoefficient(person);
        assertThat(insuranceLimitCoefficient).isEqualTo(BigDecimal.ONE);
        verifyNoInteractions(repository);
    }

    @Test
    void shouldReturnCoefficientWhenLimitLevelIcIsCorrect() {
        ReflectionTestUtils.setField(calculator, "limitLevelIsEnabled", true);
        BigDecimal expectedCoefficient = BigDecimal.valueOf(2.0);
        TMMedicalRiskLimitLevel TMMedicalRiskLimitLevel = mock(TMMedicalRiskLimitLevel.class);
        when(TMMedicalRiskLimitLevel.getCoefficient()).thenReturn(expectedCoefficient);
        when(repository.findByMedicalRiskLimitLevelIc(person.getMedicalRiskLimitLevel()))
                .thenReturn(Optional.of(TMMedicalRiskLimitLevel));
        BigDecimal result = calculator.getInsuranceLimitCoefficient(person);
        assertEquals(expectedCoefficient, result);
    }

    @Test
    void shouldThrowExceptionWhenCountryDayRateNotFound() {
        ReflectionTestUtils.setField(calculator, "limitLevelIsEnabled", true);
        when(repository.findByMedicalRiskLimitLevelIc(agreement.getCountry())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> calculator.getInsuranceLimitCoefficient(person));
        assertEquals("Insurance limit level coefficient not found for limit level ic: " + person.getMedicalRiskLimitLevel(), exception.getMessage());
    }
}
