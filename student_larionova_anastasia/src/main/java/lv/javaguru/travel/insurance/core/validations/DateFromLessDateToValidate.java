package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.core.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@PropertySource("classpath:errorCodes.properties")
public class DateFromLessDateToValidate implements TravelRequestValidator {

    @Value("${ERROR_CODE_2}")
    String errorCode2Message;

    @Autowired DateTimeService dateTimeService;

    @Override
    public Optional<ValidationError> validator(TravelCalculatePremiumRequest request) {
        return request.getAgreementDateFrom() != null
                && request.getAgreementDateTo() != null
                && request.getAgreementDateTo().before(request.getAgreementDateFrom())
                ? Optional.of(new ValidationError("ERROR_CODE_2", errorCode2Message))
                : Optional.empty();
    }
}
