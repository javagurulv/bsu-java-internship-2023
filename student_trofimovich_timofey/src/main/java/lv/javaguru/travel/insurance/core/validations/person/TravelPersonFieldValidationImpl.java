package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.validations.TravelPersonFieldValidation;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

class TravelPersonFieldValidationImpl implements TravelPersonFieldValidation {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationError> validateList(TravelCalculatePremiumRequest request) {
        return Collections.emptyList();
    }
}
