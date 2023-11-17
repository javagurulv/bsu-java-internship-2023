package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.Collection;
import java.util.Optional;

interface TravelRequestValidation{
    Optional<ValidationError> validate(TravelCalculatePremiumRequest request);
}
