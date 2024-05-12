package lv.javaguru.travel.insurance.core.validations.get;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class CorrectUUIDValidation implements GetCommandUUIDValidation{
    @Autowired
    private ValidationErrorFactory errorFactory;

    public Optional<ValidationErrorDTO> validate(String uuid) {
        String regex = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
        return uuid == null || Pattern.matches(regex, uuid)
                ? Optional.empty()
                : Optional.of(errorFactory.buildError("ERROR_CODE_18"));
    }
}
