package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

import static lv.javaguru.travel.insurance.core.util.CheckApplicationPropertiesUtil.checkProperty;

@Component
class TravelCalculateInsuranceLimitCoefficient {
    @Autowired
    private MedicalRiskLimitLevelRepository mrllRepository;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequestV1 request) {
        try {
            return checkProperty("medical.risk.limit.level.enabled")
                    ? mrllRepository
                        .findCoefficientByLimitLevelIc
                                (
                                    request.getMedicalRiskLimitLevel()
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
