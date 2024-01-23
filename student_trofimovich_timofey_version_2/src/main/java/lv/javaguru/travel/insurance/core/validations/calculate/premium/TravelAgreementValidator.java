package lv.javaguru.travel.insurance.core.validations.calculate.premium;


import lv.javaguru.travel.insurance.core.api.dto.agreement.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;

public interface TravelAgreementValidator {
    List<ValidationErrorDTO> validate(AgreementDTO agreement);
}
