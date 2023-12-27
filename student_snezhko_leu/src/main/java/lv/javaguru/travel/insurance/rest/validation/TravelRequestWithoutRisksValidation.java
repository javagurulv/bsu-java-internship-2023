package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelRequestValidation;

import java.util.Optional;

public class TravelRequestWithoutRisksValidation implements TravelRequestValidation {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        //Optional<ValidationError> result = Optional.empty();
        if (request.getSelected_risks() == null) {
            return Optional.of(new ValidationError("selected_risks", "Must not be null!"));
        }
        else if (request.getSelected_risks().isEmpty()) {
            return Optional.of(new ValidationError("selected_risks", "Must not be empty!"));
        }
        //return result;
        return Optional.empty();
    }
}
