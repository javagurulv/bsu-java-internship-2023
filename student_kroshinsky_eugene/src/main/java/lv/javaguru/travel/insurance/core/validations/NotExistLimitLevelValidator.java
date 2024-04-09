package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.Placeholder;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class NotExistLimitLevelValidator extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Autowired
    private ClassifierValueRepository valueRepository;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return (isEmptyLimit(request) && containsMedicalRisk(request)
                && containsMedicalRiskInDB(request))
            ? Optional.of(validationErrorFactory.buildError("NOT_EXISTING_RISK",
                createListOfPlaceholders(request)))
            : Optional.empty();
    }
    private boolean containsMedicalRiskInDB(TravelCalculatePremiumRequestV1 request) {
        return valueRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL",
                request.getMedicalRiskLimitLevel()).isEmpty();
    }
    private boolean isEmptyLimit(TravelCalculatePremiumRequestV1 request) {
        return request.getMedicalRiskLimitLevel()!= null && !request.getMedicalRiskLimitLevel().isBlank();
    }
    private boolean containsMedicalRisk(TravelCalculatePremiumRequestV1 request) {
        return request.getSelectedRisks() != null && request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }
    private List<Placeholder> createListOfPlaceholders(TravelCalculatePremiumRequestV1 request){
        String medicalRisk = request.getMedicalRiskLimitLevel();
        Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK", medicalRisk);
        return List.of(placeholder);
    }
}
