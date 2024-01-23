package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelRequestValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TravelRequestPersonBirthDateNotNullValidation extends TravelRequestValidationImpl {

    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Optional<ValidationError> result = Optional.empty();

        String errorCode = "ERROR_CODE_12";
        if (request.getPersonBirthDate() == null) {
            result = Optional.of(errorFactory.buildError(errorCode));
        }

        return result;
    }
}
