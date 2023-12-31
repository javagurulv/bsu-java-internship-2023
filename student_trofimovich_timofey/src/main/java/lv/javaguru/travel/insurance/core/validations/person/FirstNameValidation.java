package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class FirstNameValidation extends TravelPersonFieldValidationImpl {
    @Autowired
    ValidationErrorFactory factory;
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isBlank())
                ? Optional.of(factory.buildError("ERROR_CODE_1"))
                : Optional.empty();
    }
}
