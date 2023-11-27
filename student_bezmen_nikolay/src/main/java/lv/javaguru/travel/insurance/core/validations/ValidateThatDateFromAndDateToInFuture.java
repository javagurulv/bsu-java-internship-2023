package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class ValidateThatDateFromAndDateToInFuture implements TravelRequestValidation{
    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        Date to = request.getAgreementDateTo();

        return (to != null && to.before(new Date()))
                ? Optional.of(new ValidationError("agreementDateTo",
                "dateTo should not be from the past"))
                : Optional.empty();
    }
}
