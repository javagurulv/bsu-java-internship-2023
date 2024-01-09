package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MedicalRiskLimitLevelValidation extends TravelPersonFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Value("${medical.risk.limit.level.enabled:false}")
    private Boolean medicalRiskLimitLevelEnabled;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreementDTO, PersonDTO personDTO) {
        return medicalRiskSelect(agreementDTO) && medicalRiskEmptyOrNull(personDTO)
                && medicalRiskLimitLevelEnabled
                ? Optional.of(buildError(personDTO))
                : Optional.empty();
    }

    private boolean medicalRiskEmptyOrNull(PersonDTO request) {
        return request.getMedicalRiskLimitLevel() == null || request.getMedicalRiskLimitLevel().isEmpty();
    }

    private boolean medicalRiskSelect(AgreementDTO request) {
        return !(request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty()) &&
                request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }
    private ValidationErrorDTO buildError(PersonDTO personalCode) {
        String code = personalCode.getPersonalCode();
        return code == null ?
                buildErrorWithoutPersonalCode()
                : buildErrorWithPersonalCode(code);
    }
    private ValidationErrorDTO buildErrorWithPersonalCode(String personalCode) {
        return validationErrorFactory
                .buildError("ERROR_CODE_14",
                        List.of(new Placeholder("PERSONAL_CODE", personalCode)));
    }
    private ValidationErrorDTO buildErrorWithoutPersonalCode() {
        return validationErrorFactory
                .buildError("ERROR_CODE_14",
                        List.of(new Placeholder("PERSONAL_CODE", "missing")));
    }
}
