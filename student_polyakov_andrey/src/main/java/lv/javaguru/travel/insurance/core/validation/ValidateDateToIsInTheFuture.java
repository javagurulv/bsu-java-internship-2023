package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class ValidateDateToIsInTheFuture implements TravelRequestValidation{
    @Autowired
    private DateTimeService dateTimeService;
    @Override
    public Optional<ValidationError> validation(TravelCalculatePremiumRequest request) {
        Date todayDateAndTime = dateTimeService.getTodaysDateAndTime();
        Date agreementDateTo = request.getAgreementDateTo();
        return (agreementDateTo != null && agreementDateTo.before(todayDateAndTime))
                ? Optional.of(new ValidationError("agreementDateTo", "Must be in the future!"))
                : Optional.empty();
    }
}
