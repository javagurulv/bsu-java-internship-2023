package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.DataTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
public class DateToInFutureValidation implements TravelRequestValidation{
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date agreementDateTo = request.getAgreementDateTo();
        Date curDate = new Date();
        DataTimeService timeService = new DataTimeService();

        return ((agreementDateTo != null) && timeService.getDaysBetween(curDate, agreementDateTo) < 0)
                ? Optional.of(new ValidationError("agreementDateTo", "A date must be in future!"))
                : Optional.empty();
    }
}
