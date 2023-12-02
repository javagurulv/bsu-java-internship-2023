package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.domain.ClassifierValue;
import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class MedicalRiskLimitLevelValidation extends TravelRequestValidationImpl {
    @Value("${medical.risk.limit.level.enabled}")
    private boolean limitLevelIsSet;
    @Autowired ValidationErrorFactory factory;
    @Autowired
    ClassifierValueRepository repository;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        if (!limitLevelIsSet || !containsTravelMedicalRisk(request)) return Optional.empty();
        Optional<ValidationError> emptyError = validateEmpty(request);
        return emptyError.isPresent() ? emptyError : validateLimitIsSupported(request);
    }

    private boolean containsTravelMedicalRisk(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks() != null
                && request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }

    private Optional<ValidationError> validateLimitIsSupported(TravelCalculatePremiumRequest request) {
        Optional<ClassifierValue> optional = repository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL", request.getMedicalRiskLimitLevel());
        return (optional.isPresent())
                ? Optional.empty()
                : Optional.of(factory.buildError("ERROR_CODE_15",
                List.of(new Placeholder("value", request.getMedicalRiskLimitLevel()))));
    }

    private Optional<ValidationError> validateEmpty(TravelCalculatePremiumRequest request) {
        return (request.getMedicalRiskLimitLevel() == null || request.getMedicalRiskLimitLevel().isBlank())
                ? Optional.of(factory.buildError("ERROR_CODE_14"))
                : Optional.empty();
    }


}