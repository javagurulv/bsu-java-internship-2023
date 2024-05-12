package lv.javaguru.travel.insurance.core.validations.get;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UUIDNotNullValidation implements GetCommandUUIDValidation{
    @Autowired
    private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(String uuid) {
        return (uuid == null)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_16"))
                : Optional.empty();
    }
}
