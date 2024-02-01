package lv.javaguru.travel.insurance.core.validator;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DateFromLessThanDateToValidation implements TravelRequestValidation{
    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null && request.getAgreementDateTo().compareTo(request.getAgreementDateFrom()) < 0)
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be less than agreementDateTo!"))
                : Optional.empty();
    }
}
