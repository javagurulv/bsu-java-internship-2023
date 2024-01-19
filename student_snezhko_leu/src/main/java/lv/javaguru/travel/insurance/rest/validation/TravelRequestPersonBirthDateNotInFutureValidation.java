package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class TravelRequestPersonBirthDateNotInFutureValidation extends TravelRequestValidationImpl{

    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Optional<ValidationError> result = Optional.empty();

        if (request.getPersonBirthDate() == null) {
            return result;
        }

        String errorCode = "ERROR_CODE_13";
        if (request.getPersonBirthDate().after(new Date())) {
            result = Optional.of(errorFactory.buildError(errorCode));
        }
        return result;
    }
}
