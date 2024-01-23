package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static lv.javaguru.travel.insurance.core.util.CheckApplicationPropertiesUtil.checkProperty;

@Component
class TravelCalculateInsuranceLimitCoefficient {
    @Autowired
    private MedicalRiskLimitLevelRepository mrllRepository;

    public Double calculatePremium(TravelCalculatePremiumRequest request) {
        try {
            return checkProperty("medical.risk.limit.level.enabled")
                    ? mrllRepository
                        .findCoefficientByLimitLevelIc
                                (
                                    request.getMedicalRiskLimitLevel()
                                )
                        .get()
                        .getCoefficient()
                    : 1d;
        }
        catch (IOException e) {
            return 1d;
        }
    }
}
