package lv.javaguru.travel.insurance.core.validations;

import lombok.RequiredArgsConstructor;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class MedicalRiskLimitLevelValidation extends TravelRequestValidationImpl {

    @Value("${medical.risk.limit.level.enabled:false}")
    private Boolean medicalRiskLimitLevelEnabled;

    private final ValidationErrorFactory errorFactory;

    private final ClassifierValueRepository repository;

    @Override
    public Optional<ValidationError> check(TravelCalculatePremiumRequest request) {

        if (
            !medicalRiskLimitLevelEnabled ||
            request.getMedicalRiskLimitLevel() == null
        ) return Optional.empty();

        return (repository.findByClassifierTitleAndIc(
                "MEDICAL_RISK_LIMIT_LEVEL",
                            request.getMedicalRiskLimitLevel()).isEmpty()
                )
                ? Optional.of(errorFactory.buildError("ERROR_CODE_15"))
                : Optional.empty();
    }
}
