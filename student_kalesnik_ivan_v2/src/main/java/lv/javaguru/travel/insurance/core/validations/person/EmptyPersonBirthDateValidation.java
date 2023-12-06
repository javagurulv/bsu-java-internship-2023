package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class EmptyPersonBirthDateValidation extends PersonFieldValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDto> validate(PersonDto request,  AgreementDto request2) {
        return (personBirthDateIsNull(request))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_11"))
                : Optional.empty();
    }

    private boolean personBirthDateIsNull(PersonDto request) {
        return request.getPersonBirthDate() == null;
    }

}
