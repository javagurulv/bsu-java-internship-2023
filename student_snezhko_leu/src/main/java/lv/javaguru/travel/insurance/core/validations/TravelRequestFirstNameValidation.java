package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;
//import lv.javaguru.travel.insurance.rest.TravelRequestValidation;

import java.util.Optional;

@Component
public class TravelRequestFirstNameValidation implements TravelRequestValidation {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        if (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty()) {
            return Optional.of(new ValidationError("personFirstName", "Must not be empty!"));
        }
        return Optional.empty();
    }
}
