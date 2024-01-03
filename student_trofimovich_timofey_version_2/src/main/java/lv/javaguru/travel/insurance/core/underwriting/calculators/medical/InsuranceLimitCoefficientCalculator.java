package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;



import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.domain.MedicalRiskLimitLevel;
import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class InsuranceLimitCoefficientCalculator {
    @Value("${medical.risk.limit.level.enabled}")
    private boolean limitLevelIsEnabled;
    @Autowired
    private MedicalRiskLimitLevelRepository repository;


     BigDecimal getInsuranceLimitCoefficient(PersonDTO person) {
        return limitLevelIsEnabled ? getCoefficientFromDB(person) : getDefaultCoefficient();
    }

    private BigDecimal getCoefficientFromDB(PersonDTO person) {
        Optional<MedicalRiskLimitLevel> limitLevel = repository.findByMedicalRiskLimitLevelIc(person.getMedicalRiskLimitLevel());
        return limitLevel.map(MedicalRiskLimitLevel::getCoefficient)
                .orElseThrow(() -> new RuntimeException("Insurance limit level coefficient not found for limit level ic: "
                        + person.getMedicalRiskLimitLevel()));
    }

    private BigDecimal getDefaultCoefficient() {
        return BigDecimal.ONE;
    }
}
