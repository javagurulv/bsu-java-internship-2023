package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MedicalRiskLimitLevelValidation extends TravelAgreementFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Value( "${medical.risk.limit.level.enabled:false}" )
    private Boolean medicalRiskLimitLevelEnabled;
@Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO request) {
        return medicalRiskSelect(request) && medicalRiskEmptyOrNull(request)
                && medicalRiskLimitLevelEnabled ?
                Optional.of(validationErrorFactory.buildError("ERROR_CODE_14"))
                : Optional.empty();
    }
    private boolean medicalRiskEmptyOrNull(AgreementDTO request) {
        return request.getMedicalRiskLimitLevel()==null || request.getMedicalRiskLimitLevel().isEmpty();
    }
    private boolean medicalRiskSelect(AgreementDTO request) {
        return !(request.getSelectedRisks() == null ||request.getSelectedRisks().isEmpty()) &&
                request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }
}
