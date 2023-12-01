package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;

public interface TravelAgreementUuidValidator {

    List<ValidationErrorDTO> validate(String uuid);

}
