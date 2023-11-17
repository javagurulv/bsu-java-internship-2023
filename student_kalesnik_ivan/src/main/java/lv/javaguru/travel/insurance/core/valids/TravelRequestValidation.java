package lv.javaguru.travel.insurance.core.valids;

import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;

import java.util.Optional;

public interface TravelRequestValidation {
    Optional<ValidationError> execute(TravelCalculatePremiumRequest request);

}
