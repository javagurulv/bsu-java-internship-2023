package lv.javaguru.travel.insurance.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.core.repositories.MedicalRiskLimitLevelRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class TravelCalculateInsuranceLimitCoefficient {
    @Autowired
    private MedicalRiskLimitLevelRepository mrllRepository;

    public Double calculatePremium(TravelCalculatePremiumRequest request) {
        return mrllRepository
                .findCoefficientByLimitLevelIc(
                        request.getMedicalRiskLimitLevel()
                )
                .get()
                .getCoefficient();
    }
}
