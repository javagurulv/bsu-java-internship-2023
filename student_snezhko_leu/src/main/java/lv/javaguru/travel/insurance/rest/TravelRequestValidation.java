package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.core.ValidationError;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TravelRequestValidation {
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request);
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request);
}
