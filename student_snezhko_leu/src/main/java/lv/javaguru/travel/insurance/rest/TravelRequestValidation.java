package lv.javaguru.travel.insurance.rest;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.util.List;
import java.util.Optional;

public interface TravelRequestValidation {
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request);
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request);
}
