package lv.javaguru.travel.insurance.core.validations.get;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GetCommandUUIDValidator {
    @Autowired
    private List<GetCommandUUIDValidation> validations;

    public List<ValidationErrorDTO> validate(String uuid) {
        return validations.stream()
                .map(validation ->
                    {
                        return validation.validate(uuid);
                    }
                    )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
