package lv.javaguru.travel.insurance.core.validations.calculate;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.util.Optional;

public interface TravelPersonFieldValidation {
    public Optional<ValidationErrorDTO> validate(PersonDTO person);
    public List<ValidationErrorDTO> validateList(PersonDTO person);
}
