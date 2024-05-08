package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

//class will remove

//@Component
public class PersonIcValidation {//extends TravelPersonFieldValidationImpl{
    @Autowired
    private ValidationErrorFactory errorFactory;

  //  @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        if (person.getPersonIc() == null || person.getPersonIc().isEmpty()) {
            return Optional.of(errorFactory.buildError("ERROR_CODE_16"));
        }
        return Optional.empty();
    }
}
