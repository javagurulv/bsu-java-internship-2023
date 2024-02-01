package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import java.math.BigDecimal;
import java.util.Optional;
import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculateInsuranceLimitCoefficientTest {
    @InjectMocks
    private TravelCalculateInsuranceLimitCoefficient calculator = new TravelCalculateInsuranceLimitCoefficient();

    @Mock
    private MedicalRiskLimitLevelRepository mrllRepository = mock(MedicalRiskLimitLevelRepository.class);

    TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

    @Test
    public void TravelCalculateInsuranceLimitCoefficientCorrectValueTest() {
        init("LEVEL_10000", 1.0);
        assertEquals(BigDecimal.valueOf(1.0), calculator.calculatePremium(request));
    }

    private void init(String requestLimitLevelValue, Double expectedValue) {
        when(request.getMedicalRiskLimitLevel()).thenReturn(requestLimitLevelValue);

        MedicalRiskLimitLevel mrll = mock(MedicalRiskLimitLevel.class);
        when(mrll.getMedicalRiskLimitLevelIc()).thenReturn(requestLimitLevelValue);
        when(mrll.getCoefficient()).thenReturn(BigDecimal.valueOf(expectedValue));

        when(mrllRepository.findCoefficientByLimitLevelIc(requestLimitLevelValue)).thenReturn(Optional.of(mrll));

        ReflectionTestUtils.setField(calculator, "mrllRepository", mrllRepository);
    }
}
