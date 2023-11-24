package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelRequestValidation;

import java.util.Date;
import java.util.Optional;

public class TravelRequestDateFromValidation implements TravelRequestValidation {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateFrom() == null) {
            return Optional.of(new ValidationError("agreementDateFrom", "Must not be null!"));
        }
        else if (request.getAgreementDateFrom().compareTo(new Date()) <= 0) {
            return Optional.of(new ValidationError("agreementDateFrom", "Must not be in the past!"));
        }
        return Optional.empty();
    }
}
