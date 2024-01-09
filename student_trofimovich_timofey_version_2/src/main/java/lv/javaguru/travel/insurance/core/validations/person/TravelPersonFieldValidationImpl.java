package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.person.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

class TravelPersonFieldValidationImpl implements TravelPersonFieldValidation {
    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(PersonDTO person) {
        return Collections.emptyList();
    }
}
