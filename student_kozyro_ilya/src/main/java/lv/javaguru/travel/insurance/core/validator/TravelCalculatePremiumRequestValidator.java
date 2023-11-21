package lv.javaguru.travel.insurance.core.validator;

import lombok.AllArgsConstructor;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.validation.AbstractValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class TravelCalculatePremiumRequestValidator extends AbstractValidator<TravelCalculatePremiumRequest> {

    @Override
    public ArrayList<ValidationError> validate(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        ArrayList<ValidationError> errors = new ArrayList<>();

        validatePersonFirstName(travelCalculatePremiumRequest).ifPresent(errors::add);
        validatePersonLastName(travelCalculatePremiumRequest).ifPresent(errors::add);
        validateAgreementDateFrom(travelCalculatePremiumRequest).ifPresent(errors::add);
        validateAgreementDateTo(travelCalculatePremiumRequest).ifPresent(errors::add);

        validateDatesField(travelCalculatePremiumRequest).ifPresent(errors::add);

        return errors;
    }

    public Optional<ValidationError> validatePersonFirstName(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        return validateMandatoryField("personFirstName", travelCalculatePremiumRequest.getPersonFirstName());
    }
    public Optional<ValidationError> validatePersonLastName(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        return validateMandatoryField("personLastName", travelCalculatePremiumRequest.getPersonLastName());
    }
    public Optional<ValidationError> validateAgreementDateFrom(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        return validateMandatoryField("agreementDateFrom", travelCalculatePremiumRequest.getAgreementDateFrom());
    }
    public Optional<ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        return validateMandatoryField("agreementDateTo", travelCalculatePremiumRequest.getAgreementDateTo());
    }

    public Optional<ValidationError> validateDatesField(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        var from = travelCalculatePremiumRequest.getAgreementDateFrom();
        var to = travelCalculatePremiumRequest.getAgreementDateTo();

        return ((from != null && to != null) && (from.after(to) || from.equals(to))) ?
                Optional.of(new ValidationError("agreementDayFrom", "Must be before agreementDayTo")) :
                Optional.empty();

    }

}
