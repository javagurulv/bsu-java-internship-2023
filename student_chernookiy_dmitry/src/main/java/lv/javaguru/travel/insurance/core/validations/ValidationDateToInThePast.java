package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class ValidationDateToInThePast implements TravelRequestValidation {

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        Date dateTo = request.getAgreementDateTo();
        Date nowDate = new Date();

        if (dateTo != null && dateTo.getTime() <= nowDate.getTime()) {
            return Optional.of(new ValidationError("dateTo", "The dateTo in the past is invalid"));
        }

        return Optional.empty();
    }

}
