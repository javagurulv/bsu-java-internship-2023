package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class ValidateDateToWithDateFrom implements TravelRequestValidation {

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        Date dateTo = request.getAgreementDateTo();
        Date dateFrom = request.getAgreementDateFrom();

        if (dateFrom != null && dateTo != null) {
            if (dateFrom.before(dateTo)) {
                return Optional.empty();
            } else {
                return Optional.of(
                        new ValidationError("Date to or date from", "Date to must be after date from"));
            }
        }
        return Optional.empty();
    }
}
