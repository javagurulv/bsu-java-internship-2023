package lv.javaguru.travel.insurance.rest.validation;

import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelRequestValidation;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

public class TravelRequestWithoutRisksValidation implements TravelRequestValidation {
    @Autowired
    ValidationErrorsUtil util;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) throws IOException {
        //Optional<ValidationError> result = Optional.empty();
        util = new ValidationErrorsUtil();
        if (request.getSelected_risks() == null) {
            String errorCode = "ERROR_CODE_8";
            return Optional.of(new ValidationError(errorCode, util.getDescriptionByErrorCode(errorCode)));
        }
        else if (request.getSelected_risks().isEmpty()) {
            String errorCode = "ERROR_CODE_7";
            return Optional.of(new ValidationError(errorCode, util.getDescriptionByErrorCode(errorCode)));
        }
        //return result;
        return Optional.empty();
    }
}
