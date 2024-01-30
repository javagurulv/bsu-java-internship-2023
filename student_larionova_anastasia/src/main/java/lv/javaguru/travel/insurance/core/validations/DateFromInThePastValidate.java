package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.core.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DateFromInThePastValidate implements TravelRequestValidator {

    @Autowired DateTimeService dateTimeService;
    @Autowired private ValidationErrorFactory validationError;

    @Override
    public Optional<ValidationError> validator(TravelCalculatePremiumRequest request) {
        return request.getAgreementDateFrom() != null
                && request.getAgreementDateFrom().before(dateTimeService.getCurrentDateTime())
                ? Optional.of(validationError.buildError("ERROR_CODE_1"))
                : Optional.empty();
    }
}
