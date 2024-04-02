package lv.javaguru.travel.insurance.core.validation;

import lv.javaguru.travel.insurance.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class ValidatePersonBirthDateIsInThePast extends TravelRequestValidationImpl{
    @Autowired
    ValidationErrorFactory validationErrorFactory;
    @Autowired
    DateTimeUtil dateTimeUtil;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date personBirthDate = request.getPersonBirthDate();
        Date currentDateTime = dateTimeUtil.getTodaysDateAndTime();
        return (personBirthDate != null && personBirthDate.after(currentDateTime))
                ? Optional.of(validationErrorFactory.createError("ERROR_CODE_12"))
                : Optional.empty();
    }
}
