package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.core.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DateFromLessDateToValidate implements TravelRequestValidator {
    @Autowired DateTimeService dateTimeService;

    @Override
    public Optional<ValidationError> validator(TravelCalculatePremiumRequest request) {
        return request.getAgreementDateFrom() != null
                && request.getAgreementDateTo() != null
                && request.getAgreementDateTo().before(request.getAgreementDateFrom()) // или наоборот???
                ? Optional.of(new ValidationError("agreementDateFrom and agreementDateTo", "AgreementDateFrom should be less than AgreementDateTo!"))
                : Optional.empty();
    }
}
