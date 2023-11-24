package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.Date;
import java.util.Optional;

public class TravelCalculateDateToPastValidator implements  TravelRequestValidation{
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date currentTime = new Date();
        return (request.getAgreementDateTo() != null && request.getAgreementDateTo().before(currentTime))
                ? Optional.of(new ValidationError("agreementDateDifference", "Date from past"))
                : Optional.empty();
    }
}
