package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UUIDValidation {
    List<ValidationErrorDto> validate(String uuid);
}
