package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.core.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Component
public class DateFromInThePastValidate implements TravelRequestValidator {

    @Autowired DateTimeService dateTimeService;

    @Override
    public Optional<ValidationError> validator(TravelCalculatePremiumRequest request) {
        return request.getAgreementDateFrom() != null
                && request.getAgreementDateFrom().before(dateTimeService.getCurrentDateTime())
                ? Optional.of(new ValidationError("agreementDateFrom", "AgreementDateFrom should only be from the future"))
                : Optional.empty();
    }
}
