package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.Date;
import java.util.Optional;

public class TravelCalculateDateFromPastValidator implements TravelRequestValidation{
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date currentTime = new Date();
        return (request.getAgreementDateFrom() != null && request.getAgreementDateFrom().before(currentTime))
                ? Optional.of(new ValidationError("agreementDateDifference", "Date from past"))
                : Optional.empty();
    }
}
