package lv.javaguru.travel.insurance.core.validations.calculate.person;

import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.calculate.TravelPersonFieldValidation;

import java.util.List;
import java.util.Optional;

public abstract class TravelPersonFieldValidationImpl implements TravelPersonFieldValidation {
    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO person) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(PersonDTO person) {
        return null;
    }
}
