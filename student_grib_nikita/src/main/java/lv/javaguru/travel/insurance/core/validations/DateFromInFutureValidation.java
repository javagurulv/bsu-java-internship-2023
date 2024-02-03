package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.DataTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
public class DateFromInFutureValidation implements TravelRequestValidation{
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date curDate = new Date();
        DataTimeService timeService = new DataTimeService();

        return ((agreementDateFrom != null) && timeService.getDaysBetween(curDate, agreementDateFrom) < 0)
                ? Optional.of(new ValidationError("agreementDateFrom", "A date must be in future!"))
                : Optional.empty();
    }
}
