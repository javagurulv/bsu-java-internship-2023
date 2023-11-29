package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PersonFirstNameValidation extends TravelPersonFieldValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_7"))
                : Optional.empty();
    }

}
