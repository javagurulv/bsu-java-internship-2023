package lv.javaguru.travel.insurance.core.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MedicalRiskLimitLevelEnabledUtilTest {
    @InjectMocks
    MedicalRiskLimitLevelEnabledUtil medicalRiskLimitLevelEnabledUtil;
    @Mock
    Environment environment;
    @Test
    public void descriptionByErrorCode(){
        when(environment.getProperty("medical.risk.limit.level.enabled")).thenReturn("true");
        assertTrue(medicalRiskLimitLevelEnabledUtil.isMedicalRiskLimitLevelEnabled());
    }
}
