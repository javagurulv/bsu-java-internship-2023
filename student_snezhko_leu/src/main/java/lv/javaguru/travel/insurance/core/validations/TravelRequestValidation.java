package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;

import java.util.Optional;

interface TravelRequestValidation {
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request);
}
