package lv.javaguru.travel.insurance.core.validations.uuid;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.core.util.Placeholder;
import lv.javaguru.travel.insurance.core.validations.TravelUuidValidation;
import lv.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UuidInCorrectPatternValidation implements TravelUuidValidation {
    @Autowired
    private ValidationErrorFactory validationErrorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(String uuid) {
        return uuid != null && !uuid.matches("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$") ?
                Optional.of(validationErrorFactory.buildError("ERROR_CODE_19", List.of(new Placeholder("UUID", uuid))))
                : Optional.empty();
    }
}
