package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class ValidateDateFromIsInTheFuture implements TravelRequestValidation{
    @Autowired
    private DateTimeService dateTimeService;
    @Override
    public Optional<ValidationError> validation(TravelCalculatePremiumRequest request) {
        Date todayDateAndTime = dateTimeService.getTodaysDateAndTime();
        Date agreementDateFrom = request.getAgreementDateFrom();
        return (agreementDateFrom != null && agreementDateFrom.before(todayDateAndTime))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be in the future!"))
                : Optional.empty();
    }
}
