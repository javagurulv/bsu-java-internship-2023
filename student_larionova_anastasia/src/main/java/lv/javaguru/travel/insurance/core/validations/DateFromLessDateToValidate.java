package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DateFromLessDateToValidate implements TravelRequestValidator {

    @Autowired private ValidationErrorFactory validationError;

    @Autowired
    DateTimeUtil dateTimeService;

    @Override
    public Optional<ValidationError> validator(TravelCalculatePremiumRequest request) {
        return request.getAgreementDateFrom() != null
                && request.getAgreementDateTo() != null
                && request.getAgreementDateTo().before(request.getAgreementDateFrom())
                ? Optional.of(validationError.buildError("ERROR_CODE_2"))
                : Optional.empty();
    }
}
