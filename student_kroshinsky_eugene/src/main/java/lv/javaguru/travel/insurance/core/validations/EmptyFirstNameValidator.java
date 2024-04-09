package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
class EmptyFirstNameValidator extends TravelRequestValidationImpl {
    @Autowired private ValidationErrorFactory validationErrorFactory;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request){
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isBlank())
                ? Optional.of(validationErrorFactory.createValidationError("ERROR_CODE_5"))
                : Optional.empty();
    }
}
