package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TravelRequestExistMedicalRiskLimitLevelValidation extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory errorFactory;
    @Value("${medical.risk.limit.level.enabled:false}")
    private Boolean medicalRiskLimitLevelEnabled;
    @Autowired
    private ClassifierValueRepository classifierValueRepository;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return medicalRiskLevelNotEmptyOrNull(request)
                && notExistLimitLevel(request) ?
                Optional.of(buildError(request)) : Optional.empty();
    }

    private boolean medicalRiskLevelNotEmptyOrNull(TravelCalculatePremiumRequestV1 request) {
        return !(request.getMedicalRiskLimitLevel() == null || request.getMedicalRiskLimitLevel().isEmpty());
    }

    private ValidationError buildError(TravelCalculatePremiumRequestV1 request) {
        return errorFactory.buildError("ERROR_CODE_15", List.of(
                new Placeholder("NOT_EXISTING_MEDICAL_RISK_LIMIT_LEVEL",
                        request.getMedicalRiskLimitLevel())));

    }

    private boolean notExistLimitLevel(TravelCalculatePremiumRequestV1 request) {
        return classifierValueRepository.findByClassifierTitleAndIc(
                "MEDICAL_RISK_LIMIT_LEVEL", request.getMedicalRiskLimitLevel()).isEmpty();
    }
}
