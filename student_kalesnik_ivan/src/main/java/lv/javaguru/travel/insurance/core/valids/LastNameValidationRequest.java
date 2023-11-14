package lv.javaguru.travel.insurance.core.valids;

import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class LastNameValidationRequest implements TravelRequestValidation
{
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(new ValidationError("personLastName", "Must not be empty!"))
                : Optional.empty();
    }
}