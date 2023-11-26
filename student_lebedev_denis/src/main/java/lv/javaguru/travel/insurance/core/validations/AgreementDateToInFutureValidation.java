package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class AgreementDateToInFutureValidation implements TravelRequestValidation {
    @Autowired
    private DateTimeService dateTimeService;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        Date currentDate = dateTimeService.getCurrentDate();
        return (request.getAgreementDateTo() != null && request.getAgreementDateTo().before(currentDate))
                ? Optional.of(new ValidationError("agreementDateTo", "Must be in future!"))
                : Optional.empty();
    }
}
