package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelRequestValidation;

import java.util.Optional;

public class TravelRequestDateToValidation implements TravelRequestValidation {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateTo() == null) {
            return Optional.of(new ValidationError("agreementDateTo", "Must not be null!"));
        }
        else if (request.getAgreementDateTo().before(request.getAgreementDateFrom())) {
            return Optional.of(new ValidationError("agreementDateTo", "Must not be before agreementDateFrom!!!"));
        }
        return Optional.empty();
    }
}
