package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class TravelCalculateFirstNameValidator implements TravelRequestValidation {
    @Autowired
    private PropertyReader propertyReader;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request){
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(createValidationError("ERROR_CODE_5"))
                : Optional.empty();
    }
    private ValidationError createValidationError(String errorCode) {
        return new ValidationError(errorCode, propertyReader.getProperty(errorCode));
    }
}
