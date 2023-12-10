package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;

import java.util.List;
import java.util.Optional;

public interface AgreementFieldValidation {
    Optional<ValidationErrorDto> validate(AgreementDto agreement);


    List<ValidationErrorDto> validateList(AgreementDto agreement);
}
