package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class EmptyLimitLevelValidator extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Value("${medical.risk.limit.level.enabled:false}")
    private boolean medicalRiskLimitLevelEnabled;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request){
        return (medicalRiskLimitLevelEnabled && checkSelectedRisks(request)
                && checkMedicalRiskLimitLevel(request))
                ? Optional.of(validationErrorFactory.createValidationError("ERROR_CODE_12"))
                : Optional.empty();
    }
    private boolean checkSelectedRisks(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks() != null && request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }
    private boolean checkMedicalRiskLimitLevel(TravelCalculatePremiumRequest request) {
        return request.getMedicalRiskLimitLevel() == null || request.getMedicalRiskLimitLevel().isBlank();
    }
}