package lv.javaguru.travel.insurance.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Configuration
@PropertySource("classpath:application.properties")
public class MedicalRiskLimitLevelEnabledUtil {
    @Autowired
    private Environment environment;
    public boolean isMedicalRiskLimitLevelEnabled() {
        return Objects.equals(environment.getProperty("medical.risk.limit.level.enabled"), "true");
    }
}
