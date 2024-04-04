package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
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
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request){
        return (medicalRiskLimitLevelEnabled && checkSelectedRisks(request)
                && checkMedicalRiskLimitLevel(request))
                ? Optional.of(validationErrorFactory.createValidationError("ERROR_CODE_12"))
                : Optional.empty();
    }
    private boolean checkSelectedRisks(TravelCalculatePremiumRequestV1 request) {
        return request.getSelectedRisks() != null && request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }
    private boolean checkMedicalRiskLimitLevel(TravelCalculatePremiumRequestV1 request) {
        return request.getMedicalRiskLimitLevel() == null || request.getMedicalRiskLimitLevel().isBlank();
    }
}