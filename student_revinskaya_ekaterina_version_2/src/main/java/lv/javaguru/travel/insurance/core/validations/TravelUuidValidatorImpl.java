package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TravelUuidValidatorImpl implements TravelUuidValidator{
    @Autowired
    private List<TravelUuidValidation> uuidValidations;

    @Override
    public List<ValidationErrorDTO> validate(String uuid) {
        return uuidValidations.stream()
                .map(validation -> validation.validate(uuid))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
