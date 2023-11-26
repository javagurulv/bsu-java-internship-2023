package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class AgreementDateFromLessThenAgreementDateToValidation implements TravelRequestValidation {
    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null &&
                !request.getAgreementDateFrom().before(request.getAgreementDateTo()))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be less than agreementDateTo!"))
                : Optional.empty();
    }
}
