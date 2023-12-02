package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ExistMedicalRiskLimitLevelValidation extends TravelAgreementFieldValidationImpl {
    @Autowired
    private ValidationErrorFactory errorFactory;
    @Value("${medical.risk.limit.level.enabled:false}")
    private Boolean medicalRiskLimitLevelEnabled;
    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO request) {
        return medicalRiskLevelNotEmptyOrNull(request)
                && notExistLimitLevel(request) ?
                Optional.of(buildError(request)) : Optional.empty();
    }

    private boolean medicalRiskLevelNotEmptyOrNull(AgreementDTO request) {
        return !(request.getMedicalRiskLimitLevel() == null || request.getMedicalRiskLimitLevel().isEmpty());
    }

    private ValidationErrorDTO buildError(AgreementDTO request) {
        return errorFactory.buildError("ERROR_CODE_15", List.of(
                new Placeholder("NOT_EXISTING_MEDICAL_RISK_LIMIT_LEVEL",
                        request.getMedicalRiskLimitLevel())));

    }

    private boolean notExistLimitLevel(AgreementDTO request) {
        return classifierValueRepository.findByClassifierTitleAndIc(
                "MEDICAL_RISK_LIMIT_LEVEL", request.getMedicalRiskLimitLevel()).isEmpty();
    }
}
