package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class DateFromGreaterThanCurrentDateValidation implements TravelRequestValidation{

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
         return (request.getAgreementDateFrom() != null && request.getAgreementDateFrom().before(new Date()))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be greater than currentDate!"))
                : Optional.empty();
    }
}
//new Date().compareTo(request.getAgreementDateFrom()) > 0