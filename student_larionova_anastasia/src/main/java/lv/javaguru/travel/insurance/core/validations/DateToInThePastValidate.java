package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Component
@PropertySource("classpath:errorCodes.properties")
public class DateToInThePastValidate implements TravelRequestValidator {

    @Value("${ERROR_CODE_4}")
    String errorCode4Message;

    @Autowired DateTimeService dateTimeService;

    @Override
    public Optional<ValidationError> validator(TravelCalculatePremiumRequest request) {
        Date currentDateTime = dateTimeService.getCurrentDateTime();
        return (request.getAgreementDateTo() != null
                && request.getAgreementDateTo().before(currentDateTime))
                ? Optional.of(new ValidationError("ERROR_CODE_4", errorCode4Message))
                : Optional.empty();
    }

}
