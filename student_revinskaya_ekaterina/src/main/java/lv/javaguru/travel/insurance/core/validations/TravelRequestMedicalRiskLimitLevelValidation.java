package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.MedicalRiskLimitLevelEnabledUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TravelRequestMedicalRiskLimitLevelValidation extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Autowired
    private MedicalRiskLimitLevelEnabledUtil medicalRiskLimitLevelEnabledUtil;
@Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return medicalRiskSelect(request) && medicalRiskEmptyOrNull(request)
                && medicalRiskLimitLevelEnabledUtil.isMedicalRiskLimitLevelEnabled() ?
                Optional.of(validationErrorFactory.buildError("ERROR_CODE_14"))
                : Optional.empty();
    }
    private boolean medicalRiskEmptyOrNull(TravelCalculatePremiumRequest request) {
        return request.getMedicalRiskLimitLevel()==null || request.getMedicalRiskLimitLevel().isEmpty();
    }
    private boolean medicalRiskSelect(TravelCalculatePremiumRequest request) {
        return !(request.getSelectedRisks() == null ||request.getSelectedRisks().isEmpty()) &&
                request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }
}
