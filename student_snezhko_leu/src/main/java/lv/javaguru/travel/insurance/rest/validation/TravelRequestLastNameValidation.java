package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelRequestValidation;

import java.util.Optional;

public class TravelRequestLastNameValidation implements TravelRequestValidation {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        if (request.getPersonLastName() == null || request.getPersonLastName().isEmpty()) {
            return Optional.of(new ValidationError("personLastName", "Must not be empty!"));
        }
        return Optional.empty();
    }
}
