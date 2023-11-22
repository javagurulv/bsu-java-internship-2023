package lv.javaguru.travel.insurance.core.valids;

import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;

import java.util.List;
import java.util.Optional;

public interface TravelRequestValidation {
    Optional<ValidationError> validate(TravelCalculatePremiumRequest request);

    List<ValidationError> validateList(TravelCalculatePremiumRequest request);

}
