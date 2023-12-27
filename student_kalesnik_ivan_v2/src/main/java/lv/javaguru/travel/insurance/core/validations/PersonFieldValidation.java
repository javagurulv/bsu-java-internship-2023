package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;

import java.util.List;
import java.util.Optional;

public interface PersonFieldValidation {
    Optional<ValidationErrorDto> validate(PersonDto person, AgreementDto agreement);
    List<ValidationErrorDto> validateList(PersonDto person, AgreementDto agreement);
}
