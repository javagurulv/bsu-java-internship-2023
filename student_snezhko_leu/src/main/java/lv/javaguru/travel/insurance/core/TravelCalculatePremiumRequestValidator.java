package lv.javaguru.travel.insurance.core;


//import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
class TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        validateAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateTo(request).ifPresent(errors::add);
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
    private Optional<ValidationError> validateAgreementDateFrom(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateFrom() == null) {
            return Optional.of(new ValidationError("agreementDateFrom", "Must not be null!"));
        }
        else if (request.getAgreementDateFrom().compareTo(new Date()) <= 0) {
            return Optional.of(new ValidationError("agreementDateFrom", "Must not be in the past!"));
        }
        return Optional.empty();
    }
    private Optional<ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateTo() == null) {
            return Optional.of(new ValidationError("agreementDateTo", "Must not be null!"));
        }
        else if (request.getAgreementDateTo().before(request.getAgreementDateFrom())) {
            return Optional.of(new ValidationError("agreementDateTo", "Must not be before agreementDateFrom!!!"));
        }
        return Optional.empty();
    }
}