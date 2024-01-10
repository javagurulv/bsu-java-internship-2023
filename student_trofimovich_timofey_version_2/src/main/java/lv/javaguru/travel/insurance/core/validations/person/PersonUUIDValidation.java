package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonUUIDValidation extends TravelPersonFieldValidationImpl {

    @Autowired
    private ValidationErrorFactory factory;
    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        return (person.getPersonUUID() == null || person.getPersonUUID().isEmpty())
                ? Optional.of(factory.buildError("ERROR_CODE_17"))
                : Optional.empty();
    }
}
