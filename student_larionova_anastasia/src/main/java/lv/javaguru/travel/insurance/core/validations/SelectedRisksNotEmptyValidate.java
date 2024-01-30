package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@PropertySource("classpath:errorCodes.properties")
public class SelectedRisksNotEmptyValidate implements TravelRequestValidator {

    @Value("${ERROR_CODE_8}")
    String errorCode8Message;

    @Override
    public Optional<ValidationError> validator(TravelCalculatePremiumRequest request) {
        return (request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty())
                ? Optional.of(new ValidationError("ERROR_CODE_8", errorCode8Message))
                : Optional.empty();
    }
}
