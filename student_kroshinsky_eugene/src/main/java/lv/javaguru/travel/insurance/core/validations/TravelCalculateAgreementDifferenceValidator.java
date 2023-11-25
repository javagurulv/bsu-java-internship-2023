package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class TravelCalculateAgreementDifferenceValidator implements TravelRequestValidation {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null
                && request.getAgreementDateTo().before(request.getAgreementDateFrom())
        )
                ? Optional.of(new ValidationError("agreementDateDifference", "DateTo must be greater than DateFrom"))
                : Optional.empty();
    }
}
