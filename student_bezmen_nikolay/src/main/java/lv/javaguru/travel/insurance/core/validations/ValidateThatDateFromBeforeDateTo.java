package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class ValidateThatDateFromBeforeDateTo implements TravelRequestValidation{
    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        Date from = request.getAgreementDateFrom();
        Date to = request.getAgreementDateTo();
        if (from == null || to == null) {
            return Optional.empty();
        }

        return (!(from.before(to) || from.equals(to)))
                ? Optional.of(new ValidationError("agreementDateFrom, agreementDateTo",
                "dateFrom must be before dateTo"))
                : Optional.empty();
    }
}
