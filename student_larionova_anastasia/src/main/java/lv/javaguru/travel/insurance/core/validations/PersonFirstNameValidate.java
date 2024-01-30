package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@PropertySource("classpath:errorCodes.properties")
public class PersonFirstNameValidate  implements TravelRequestValidator {

    @Value("${ERROR_CODE_6}")
    String errorCode6Message;

    @Override
    public Optional<ValidationError> validator(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(new ValidationError("ERROR_CODE_6", errorCode6Message))
                : Optional.empty();
    }
}
