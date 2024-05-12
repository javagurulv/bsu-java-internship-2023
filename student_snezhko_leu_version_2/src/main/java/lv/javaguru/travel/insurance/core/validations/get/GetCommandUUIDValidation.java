package lv.javaguru.travel.insurance.core.validations.get;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.Optional;

public interface GetCommandUUIDValidation {
    public Optional<ValidationErrorDTO> validate(String uuid);
}
