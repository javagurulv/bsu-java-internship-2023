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
        validateDateFromLessThenDateTo(request).ifPresent(errors::add);
        validateDateFromNotFromPast(request).ifPresent(errors::add);
        validateDateToNotFromPast(request).ifPresent(errors::add);
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
        return request.getAgreementDateFrom() == null
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest request) {
        return request.getAgreementDateTo() == null
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateDateFromLessThenDateTo(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();
        return (dateFrom != null && dateTo != null
                && (dateFrom.equals(dateTo) || dateFrom.after(dateTo)))
                ? Optional.of(new ValidationError("agreementDateTo", "Must be after agreementDateFrom!"))
                : Optional.empty();
    }
    private Optional<ValidationError> validateDateToNotFromPast(TravelCalculatePremiumRequest request) {
        Date today = new Date();
        Date dateTo = request.getAgreementDateTo();
        return (dateTo != null && today.after(dateTo))
                ? Optional.of(new ValidationError("agreementDateTo", "Must be from future!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateDateFromNotFromPast(TravelCalculatePremiumRequest request) {
        Date today = new Date();
        Date dateFrom = request.getAgreementDateFrom();
        return (dateFrom != null && today.after(dateFrom))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be from future!"))
                : Optional.empty();
    }
}
