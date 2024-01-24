package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.List;
import java.util.Optional;

abstract public class TravelRequestValidationImpl implements TravelRequestValidation {
    @Override
    public Optional<ValidationError> check(TravelCalculatePremiumRequest request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationError> checkList(TravelCalculatePremiumRequest request) {
        return null;
    }
}
