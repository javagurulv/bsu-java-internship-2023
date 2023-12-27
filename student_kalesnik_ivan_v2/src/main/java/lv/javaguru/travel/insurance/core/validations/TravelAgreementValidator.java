package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDto;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;

import java.util.List;

public interface TravelAgreementValidator {

    List<ValidationErrorDto> validate(AgreementDto request);

}
