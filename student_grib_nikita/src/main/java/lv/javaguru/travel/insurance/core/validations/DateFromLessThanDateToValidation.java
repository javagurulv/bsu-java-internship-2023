package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
@Component
public class DateFromLessThanDateToValidation implements TravelRequestValidation{
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();
        return (agreementDateTo != null
                && agreementDateFrom != null
                && (agreementDateFrom.after(agreementDateTo) || agreementDateFrom.equals(agreementDateTo)))  // тк в условии не говорится про равенство дат!
                ? Optional.of(new ValidationError("agreementDateFrom", "AgreementDateFrom should be less than agreementDateTo!"))
                : Optional.empty();
    }
}
