package lv.javaguru.travel.insurance.rest.validation;

import lombok.Setter;
import lv.javaguru.travel.insurance.core.ValidationError;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.TravelRequestValidation;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

@Setter
public class TravelRequestFirstNameValidation implements TravelRequestValidation {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) throws IOException {
        ValidationErrorsUtil util = new ValidationErrorsUtil();
        if (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty()) {
            String errorCode = "ERROR_CODE_1";
            return Optional.of(new ValidationError(errorCode, util.getDescriptionByErrorCode(errorCode)));
        }
        return Optional.empty();
    }
}
