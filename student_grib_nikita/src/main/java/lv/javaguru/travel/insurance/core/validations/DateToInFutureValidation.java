package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.DataTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
public class DateToInFutureValidation implements TravelRequestValidation{
    @Autowired
    private DataTimeService timeService;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date agreementDateTo = request.getAgreementDateTo();
        Date curDate = timeService.getCurrentDate();

        return ((agreementDateTo != null) && agreementDateTo.before(curDate))
                ? Optional.of(new ValidationError("agreementDateTo", "A date must be in future!"))
                : Optional.empty();
    }
}
