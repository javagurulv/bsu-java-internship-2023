package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.repositories.ClassifierValueRepository;
import lv.javaguru.travel.insurance.dto.Placeholder;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TravelCalculateNotExistLimitValidator extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Autowired
    private ClassifierValueRepository valueRepository;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        if (isEmptyLimit(request) && containsMedicalRisk(request) && containsMedicalRiskInDB(request)) {
            Placeholder placeholder = new Placeholder("NOT_EXISTING_RISK", request.getMedicalRiskLimitLevel());
            return Optional.of(validationErrorFactory.buildError("NOT_EXISTING_RISK", List.of(placeholder)));
        } else {
            return Optional.empty();
        }
    }
    private boolean containsMedicalRiskInDB(TravelCalculatePremiumRequest request) {
        return valueRepository.findByClassifierTitleAndIc("MEDICAL_RISK_LIMIT_LEVEL",
                request.getMedicalRiskLimitLevel()).isEmpty();
    }
    private boolean isEmptyLimit(TravelCalculatePremiumRequest request) {
        return request.getMedicalRiskLimitLevel()!= null && !request.getMedicalRiskLimitLevel().isBlank();
    }
    private boolean containsMedicalRisk(TravelCalculatePremiumRequest request){
        return request.getSelectedRisks() != null && request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }
}
