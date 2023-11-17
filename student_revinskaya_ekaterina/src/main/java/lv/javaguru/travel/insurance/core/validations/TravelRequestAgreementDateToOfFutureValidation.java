package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
public class TravelRequestAgreementDateToOfFutureValidation implements TravelRequestValidation{
    private DateTimeService dateTimeService = new DateTimeService();
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date dateTo = request.getAgreementDateTo();
        return (dateTo != null && (dateTimeService.getCurrentDateTime().after(dateTo)))
                ? Optional.of(new ValidationError("agreementDateTo", "agreementDateTo must be future date"))
                : Optional.empty();
    }
}
