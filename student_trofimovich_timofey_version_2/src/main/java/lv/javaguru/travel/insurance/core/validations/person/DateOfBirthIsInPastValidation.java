package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component

public class DateOfBirthIsInPastValidation extends TravelPersonFieldValidationImpl {
    @Autowired
    ValidationErrorFactory errorFactory;
    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        Date birthDate = person.getPersonBirthDate();
        return (birthDate != null && birthDate.after(new Date()))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_13"))
                : Optional.empty();
    }
}
