package lv.javaguru.travel.insurance.core.validations.person;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.TravelAgreementFieldValidation;
import lv.javaguru.travel.insurance.core.validations.TravelPersonFieldValidation;

import java.util.List;
import java.util.Optional;

abstract class TravelPersonFieldValidationImpl
        implements TravelPersonFieldValidation {

    @Override
    public Optional<ValidationErrorDTO> validate(PersonDTO request) {
        return Optional.empty();
    }

    @Override
    public List<ValidationErrorDTO> validateList(PersonDTO request) {
        return List.of();
    }

}