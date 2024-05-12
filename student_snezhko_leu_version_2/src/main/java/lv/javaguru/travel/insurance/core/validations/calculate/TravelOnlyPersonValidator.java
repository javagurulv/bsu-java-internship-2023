package lv.javaguru.travel.insurance.core.validations.calculate;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;

import java.util.List;

public interface TravelOnlyPersonValidator {
    public List<ValidationErrorDTO> validate(AgreementDTO agreement);
}
