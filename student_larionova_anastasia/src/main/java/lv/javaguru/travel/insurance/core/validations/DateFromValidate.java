package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@PropertySource("classpath:errorCodes.properties")
public class DateFromValidate implements TravelRequestValidator{

    @Value("${ERROR_CODE_3}")
    String errorCode3Message;

    @Override
    public Optional<ValidationError> validator(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationError("ERROR_CODE_3", errorCode3Message))
                : Optional.empty();
    }
}
