package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class TravelCalculateDateFromPastValidator implements TravelRequestValidation {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date currentTime = new Date();
        return (request.getAgreementDateFrom() != null && request.getAgreementDateFrom().before(currentTime))
                ? Optional.of(new ValidationError("agreementDateDifference", "Date from past"))
                : Optional.empty();
    }
}
