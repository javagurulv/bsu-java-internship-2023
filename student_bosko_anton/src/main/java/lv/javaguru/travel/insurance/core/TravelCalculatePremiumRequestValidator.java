package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        validateAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateTo(request).ifPresent(errors::add);
        validateFromLessTo(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validatePersonFirstName(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty()
                ? Optional.of(new ValidationError("personFirstName", "cannot be empty!"))
                : Optional.empty());
    }

    private Optional<ValidationError> validatePersonLastName(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty()
                ? Optional.of(new ValidationError("personLastName", "cannot be empty!"))
                : Optional.empty());
    }

    private Optional<ValidationError> validateAgreementDateFrom(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null
                ? Optional.of(new ValidationError("agreementDateFrom", "cannot be empty!"))
                : Optional.empty());
    }

    private Optional<ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null
                ? Optional.of(new ValidationError("agreementDateTo", "cannot be empty!"))
                : Optional.empty());
    }

    private Optional<ValidationError> validateFromLessTo(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() != null
                && request.getAgreementDateTo() != null
                && request.getAgreementDateTo().getTime() <= request.getAgreementDateFrom().getTime()
                ? Optional.of(new ValidationError("agreementDateTo", "needs to be bigger than agreementDateFrom"))
                : Optional.empty());
    }
}
