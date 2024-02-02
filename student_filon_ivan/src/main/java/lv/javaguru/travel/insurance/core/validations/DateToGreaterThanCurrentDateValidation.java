package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class DateToGreaterThanCurrentDateValidation implements TravelRequestValidation{

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() != null && request.getAgreementDateTo().before(new Date()))
                ? Optional.of(new ValidationError("agreementDateTo", "Must be greater than currentDate!"))
                : Optional.empty();
    }
}
