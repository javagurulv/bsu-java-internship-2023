package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidateDateFromLessThanDateTo implements TravelRequestValidation{
    @Override
    public Optional<ValidationError> validation(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null
                && (request.getAgreementDateFrom().equals(request.getAgreementDateTo()) || request.getAgreementDateFrom().after(request.getAgreementDateTo())))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be less then agreementDateTo!"))
                : Optional.empty();
    }
}
