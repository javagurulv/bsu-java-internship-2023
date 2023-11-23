package lv.javaguru.travel.insurance.core;

import lombok.NoArgsConstructor;
import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.boot.autoconfigure.data.couchbase.CouchbaseDataAutoConfiguration;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Component
public class TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {

        List<ValidationError> errors = new ArrayList<ValidationError>();

        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        validateAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateTo(request).ifPresent(errors::add);
        validateDateSequence(request).ifPresent(errors::add);

        return errors;
    }

    private Optional<ValidationError> validatePersonFirstName(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().trim().isEmpty())
                ? Optional.of(new ValidationError("personFirstName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validatePersonLastName(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().trim().isEmpty())
                ? Optional.of(new ValidationError("personLastName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateAgreementDateFrom(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateDateSequence(TravelCalculatePremiumRequest request) {

        if (request.getAgreementDateFrom() == null || request.getAgreementDateTo() == null)
            return Optional.empty();

        return (request.getAgreementDateTo().getTime() - request.getAgreementDateFrom().getTime() < 0)
                ? Optional.of(new ValidationError("agreementDateTo", "Must be after agreementDateFrom!"))
                : Optional.empty();
    }
}
