package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class TravelCalculateFutureDateOfBirthValidator extends TravelRequestValidationImpl{
    @Autowired
    private ValidationErrorFactory validationErrorFactory;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date currentTime = new Date();
        return (request.getDateOfBirth() != null && request.getDateOfBirth().after(currentTime))
                ? Optional.of(validationErrorFactory.createValidationError("ERROR_CODE_10"))
                : Optional.empty();
    }
}
