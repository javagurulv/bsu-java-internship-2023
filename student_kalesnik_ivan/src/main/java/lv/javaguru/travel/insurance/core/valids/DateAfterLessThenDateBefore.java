package lv.javaguru.travel.insurance.core.valids;

import lv.javaguru.travel.insurance.validation.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.validation.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class DateAfterLessThenDateBefore implements TravelRequestValidation {

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() != null
                && request.getAgreementDateTo() != null
                && (request.getAgreementDateFrom().equals(request.getAgreementDateTo())
                || request.getAgreementDateFrom().after(request.getAgreementDateTo())))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be less then agreementDateTo!"))
                : Optional.empty();
    }

}