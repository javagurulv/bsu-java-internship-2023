package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.ValidationError;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        validateAgreementDataFrom(request).ifPresent(errors::add);
        validateAgreementDataTo(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validatePersonFirstName(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(new ValidationError("personFirstName", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<ValidationError> validatePersonLastName(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationError("personLastName", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<ValidationError> validateAgreementDataFrom(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null || request.getAgreementDateFrom().toString().isEmpty())
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<ValidationError> validateAgreementDataTo(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null || request.getAgreementDateTo().toString().isEmpty())
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be empty!"))
                : Optional.empty();
    }

}
