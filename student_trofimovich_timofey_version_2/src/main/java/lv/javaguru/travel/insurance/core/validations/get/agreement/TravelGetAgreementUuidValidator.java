package lv.javaguru.travel.insurance.core.validations.get.agreement;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import java.util.Optional;

public interface TravelGetAgreementUuidValidator {
    Optional<ValidationErrorDTO> validate(String uuid);
}
