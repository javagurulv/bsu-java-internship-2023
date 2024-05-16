package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.repositories.calculate.medical.MedicalRiskLimitLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

import static lv.javaguru.travel.insurance.core.util.CheckApplicationPropertiesUtil.checkProperty;

@Component
class TravelCalculateInsuranceLimitCoefficientMedical implements TravelRiskPremiumCalculatorMedicalComponent {
    @Autowired
    private MedicalRiskLimitLevelRepository mrllRepository;

    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        try {
            return checkProperty("medical.risk.limit.level.enabled")
                    ? mrllRepository
                        .findByMedicalRiskLimitLevelIc
                                (person.getMedicalRiskLimitLevel()
                                )
                        .get()
                        .getCoefficient()
                    : BigDecimal.ONE;
        }
        catch (IOException e) {
            return BigDecimal.ONE;
        }
    }
}
