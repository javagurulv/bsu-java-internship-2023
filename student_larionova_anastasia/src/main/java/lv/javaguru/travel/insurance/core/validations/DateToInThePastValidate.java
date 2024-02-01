package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class DateToInThePastValidate implements TravelRequestValidator {

    @Autowired private ValidationErrorFactory validationError;

    @Autowired
    DateTimeUtil dateTimeService;

    @Override
    public Optional<ValidationError> validator(TravelCalculatePremiumRequest request) {
        Date currentDateTime = dateTimeService.getCurrentDateTime();
        return (request.getAgreementDateTo() != null
                && request.getAgreementDateTo().before(currentDateTime))
                ? Optional.of(validationError.buildError("ERROR_CODE_4"))
                : Optional.empty();
    }

}
