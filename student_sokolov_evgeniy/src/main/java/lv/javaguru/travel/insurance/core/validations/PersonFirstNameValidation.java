package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PersonFirstNameValidation extends TravelRequestValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_7"))
                : Optional.empty();
    }

}
