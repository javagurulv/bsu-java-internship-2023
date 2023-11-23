package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class DateFromIsInFutureValidation implements TravelRequestValidation {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        return (dateFrom != null && dateFrom.before(new Date()))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be the future!"))
                : Optional.empty();
    }
}
