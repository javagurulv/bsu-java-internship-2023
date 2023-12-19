package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TravelAgreementValidator {
    @Autowired
    List<ValidationErrorDTO> validate(AgreementDTO request);
}
