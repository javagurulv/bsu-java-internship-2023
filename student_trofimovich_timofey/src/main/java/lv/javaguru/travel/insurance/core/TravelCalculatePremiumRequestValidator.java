package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
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
        validateAgreementDateToIsLessThenAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateFromIsFuture(request).ifPresent(errors::add);
        validateAgreementDateToIsFuture(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validatePersonLastName(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationError("personLastName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validatePersonFirstName(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(new ValidationError("personFirstName", "Must not be empty!"))
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
    private Optional<ValidationError> validateAgreementDateToIsLessThenAgreementDateFrom(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();
        return (dateFrom != null && dateTo != null
                && (dateFrom.equals(dateTo) || dateFrom.after(dateTo)))
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be before agreementDateFrom!"))
                : Optional.empty();
    }


    private Optional<ValidationError> validateAgreementDateFromIsFuture(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        return (dateFrom != null && dateFrom.before(new Date()))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be the future!"))
                : Optional.empty();
    }
    private Optional<ValidationError> validateAgreementDateToIsFuture(TravelCalculatePremiumRequest request) {
        Date dateTo = request.getAgreementDateTo();
        return (dateTo != null && dateTo.before(new Date()))
                ? Optional.of(new ValidationError("agreementDateTo", "Must be the future!"))
                : Optional.empty();
    }

}
