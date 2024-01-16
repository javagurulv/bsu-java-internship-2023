package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class ValidationDateFromInThePast implements TravelRequestValidation {
    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateTo();
        Date nowDate = new Date();

        if (dateFrom != null && dateFrom.getTime() <= nowDate.getTime()) {
            return Optional.of(new ValidationError("dateFrom", "The dateFrom in the past is invalid"));
        }

        return Optional.empty();    }
}
