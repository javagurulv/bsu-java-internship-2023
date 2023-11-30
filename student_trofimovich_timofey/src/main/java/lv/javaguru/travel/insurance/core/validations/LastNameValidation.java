package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class LastNameValidation extends TravelRequestValidationImpl {
    @Autowired
    ValidationErrorFactory factory;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isBlank())
                ? Optional.of(factory.buildError("ERROR_CODE_2"))
                : Optional.empty();
    }
}
