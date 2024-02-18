package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TravelCalculateInsuranceLimitCoefficientTest {
    @InjectMocks
    private TravelCalculateInsuranceLimitCoefficientMedical calculator;

    @Mock
    private MedicalRiskLimitLevelRepository mrllRepository;

    @Mock
    private AgreementDTO agreement;
    @Mock
    private PersonDTO person;

    @Test
    public void TravelCalculateInsuranceLimitCoefficientCorrectValueTest() {
        init("LEVEL_10000", 1.0);
        assertEquals(BigDecimal.valueOf(1.0), calculator.calculatePremium(agreement, person));
    }

    private void init(String requestLimitLevelValue, Double expectedValue) {
        when(agreement.getMedicalRiskLimitLevel()).thenReturn(requestLimitLevelValue);

        MedicalRiskLimitLevel mrll = mock(MedicalRiskLimitLevel.class);
        when(mrll.getMedicalRiskLimitLevelIc()).thenReturn(requestLimitLevelValue);
        when(mrll.getCoefficient()).thenReturn(BigDecimal.valueOf(expectedValue));

        when(mrllRepository.findByMedicalRiskLimitLevelIc(requestLimitLevelValue)).thenReturn(Optional.of(mrll));

        ReflectionTestUtils.setField(calculator, "mrllRepository", mrllRepository);
    }
}
