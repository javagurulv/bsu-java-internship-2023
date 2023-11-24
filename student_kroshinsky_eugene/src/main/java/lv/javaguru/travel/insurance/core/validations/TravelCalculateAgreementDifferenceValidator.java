package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.Optional;

public class TravelCalculateAgreementDifferenceValidator implements TravelRequestValidation{
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null
                && request.getAgreementDateTo().before(request.getAgreementDateFrom())
        )
                ? Optional.of(new ValidationError("agreementDateDifference", "DateTo must be greater than DateFrom"))
                : Optional.empty();
    }
}
