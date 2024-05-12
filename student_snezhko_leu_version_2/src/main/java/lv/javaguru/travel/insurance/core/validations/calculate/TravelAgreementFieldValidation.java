package lv.javaguru.travel.insurance.core.validations.calculate;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.util.Optional;

public interface TravelAgreementFieldValidation {
    public Optional<ValidationErrorDTO> validate(AgreementDTO request);
    public List<ValidationErrorDTO> validateList(AgreementDTO request);
}
