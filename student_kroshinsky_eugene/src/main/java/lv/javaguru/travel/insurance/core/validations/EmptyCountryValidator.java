package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class EmptyCountryValidator extends TravelRequestValidationImpl {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (containsMedicalRisk(request) && isCountryEmpty(request))
                ? Optional.of(validationErrorFactory.createValidationError("ERROR_CODE_8"))
                : Optional.empty();
    }
    private Boolean containsMedicalRisk(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks() != null &&
                request.getSelectedRisks().contains("TRAVEL_MEDICAL");
    }
    private Boolean isCountryEmpty(TravelCalculatePremiumRequest request) {
        return request.getCountry() == null || request.getCountry().isBlank();
    }
}
