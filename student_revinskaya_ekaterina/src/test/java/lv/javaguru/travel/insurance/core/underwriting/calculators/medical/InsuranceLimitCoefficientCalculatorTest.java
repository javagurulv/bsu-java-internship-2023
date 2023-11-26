package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import lv.javaguru.travel.insurance.core.util.MedicalRiskLimitLevelEnabledUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InsuranceLimitCoefficientCalculatorTest {
    @InjectMocks
    private InsuranceLimitCoefficientCalculator insuranceLimitCoefficientCalculator;
    @Mock
    private MedicalRiskLimitLevelEnabledUtil medicalRiskLimitLevelEnabledUtil;
    @Mock
    private MedicalRiskLimitLevelRepository medicalRiskLimitLevelRepository;
    @Mock private TravelCalculatePremiumRequest request;
    @Test
    public void calculateInsuranceLimitCoefficientTest(){
        when(medicalRiskLimitLevelEnabledUtil.isMedicalRiskLimitLevelEnabled()).thenReturn(true);
        when(request.getMedicalRiskLimitLevel()).thenReturn("LEVEL_10000");
        MedicalRiskLimitLevel medicalRiskLimitLevel = mock(MedicalRiskLimitLevel.class);
        when(medicalRiskLimitLevel.getCoefficient()).thenReturn(BigDecimal.valueOf(1.0));
        when(medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc("LEVEL_10000"))
                .thenReturn(Optional.of(medicalRiskLimitLevel));
        assertEquals(insuranceLimitCoefficientCalculator.calculate(request), BigDecimal.valueOf(1.0));
    }
    @Test
    public void shouldReturn1Test(){
        when(medicalRiskLimitLevelEnabledUtil.isMedicalRiskLimitLevelEnabled()).thenReturn(false);
        assertEquals(insuranceLimitCoefficientCalculator.calculate(request), BigDecimal.valueOf(1));
    }
    @Test
    public void throwExceptionCountryDefaultDayRateTest(){
        when(medicalRiskLimitLevelEnabledUtil.isMedicalRiskLimitLevelEnabled()).thenReturn(true);
        when(request.getMedicalRiskLimitLevel()).thenReturn("FAKE_LEVEL");
        when(medicalRiskLimitLevelRepository.findByMedicalRiskLimitLevelIc("FAKE_LEVEL"))
                .thenReturn(Optional.empty());
        Throwable thrown = assertThrows(RuntimeException.class, ()->insuranceLimitCoefficientCalculator.calculate(request));
        assertEquals(thrown.getMessage(), "medicalRiskLimitLevel with ic FAKE_LEVEL not found");
    }
}
