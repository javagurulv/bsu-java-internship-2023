package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.core.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Component
@PropertySource("classpath:errorCodes.properties")
public class DateFromInThePastValidate implements TravelRequestValidator {

    @Value("${ERROR_CODE_1}")
    String errorCode1Message;

    @Autowired DateTimeService dateTimeService;

    @Override
    public Optional<ValidationError> validator(TravelCalculatePremiumRequest request) {
        return request.getAgreementDateFrom() != null
                && request.getAgreementDateFrom().before(dateTimeService.getCurrentDateTime())
                ? Optional.of(new ValidationError("ERROR_CODE_1", errorCode1Message))
                : Optional.empty();
    }
}
