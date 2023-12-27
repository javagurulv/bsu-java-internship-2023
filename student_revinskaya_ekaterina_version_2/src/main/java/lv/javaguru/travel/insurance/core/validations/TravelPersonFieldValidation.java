package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.util.Optional;

public interface TravelPersonFieldValidation {
    Optional<ValidationErrorDTO> validate(AgreementDTO agreementDTO, PersonDTO request);

    List<ValidationErrorDTO> validateList(AgreementDTO agreementDTO, PersonDTO personDTO);

}
