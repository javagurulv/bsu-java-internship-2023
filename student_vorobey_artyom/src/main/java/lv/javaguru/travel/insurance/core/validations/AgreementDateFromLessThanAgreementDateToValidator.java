package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class AgreementDateFromLessThanAgreementDateToValidator implements TravelRequestValidation{
    @Override
    public Optional<ValidationError> validateArgs(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();
        return (dateTo != null && dateFrom != null
                && (dateFrom.equals(dateTo) || dateFrom.after(dateTo)))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be less than agreementDateTo"))
                : Optional.empty();
    }
}
