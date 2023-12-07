package lv.javaguru.travel.insurance.core.validations.agreement;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.PersonDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import lv.javaguru.travel.insurance.core.validations.AgreementFieldValidation;

import java.util.List;
import java.util.Optional;

abstract class AgreementFieldValidationImpl implements AgreementFieldValidation {

    @Override
    public Optional<ValidationErrorDto> validate(AgreementDto agreement) {
        return Optional.empty();
    }


    @Override
    public List<ValidationErrorDto> validateList(AgreementDto agreement) {
        return null;
    }

}
