package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
class TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        validateAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateTo(request).ifPresent(errors::add);
        validateAgreementDateFromLessagreementDateTo(request).ifPresent(errors::add);
        validateAgreementDateFromInThePast(request).ifPresent(errors::add);
        validateAgreementDateToInThePast(request).ifPresent(errors::add);
        validateAgreementDateFromAndDateToInThePast(request).ifPresent(errors::add);
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
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<ValidationError> validateAgreementDateFromLessagreementDateTo(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null
                && getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo()) < 0)
                ? Optional.of(new ValidationError("agreementDateFrom and agreementDateTo", "AgreementDateFrom should be less than AgreementDateTo!"))
                : Optional.empty();
    }

    private Optional<ValidationError>  validateAgreementDateFromInThePast(TravelCalculatePremiumRequest request) {
        LocalDate localDate = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        Date date = new Date(sqlDate.getTime());
        return (request.getAgreementDateFrom() != null
                && request.getAgreementDateTo() != null
                && getDaysBetween(date, request.getAgreementDateFrom()) < 0)
                ? Optional.of(new ValidationError("agreementDateFrom", "AgreementDateFrom should only be from the future"))
                : Optional.empty();
    }

    private Optional<ValidationError>  validateAgreementDateToInThePast(TravelCalculatePremiumRequest request) {
        LocalDate localDate = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        Date date = new Date(sqlDate.getTime());
        return (request.getAgreementDateFrom() != null
                && request.getAgreementDateTo() != null
                && getDaysBetween(date, request.getAgreementDateTo()) < 0)
                ? Optional.of(new ValidationError("agreementDateTo", "AgreementDateTo should only be from the future"))
                : Optional.empty();
    }

    private Optional<ValidationError>  validateAgreementDateFromAndDateToInThePast(TravelCalculatePremiumRequest request) {
        LocalDate localDate = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        Date date = new Date(sqlDate.getTime());
        return (request.getAgreementDateFrom() != null
                && request.getAgreementDateTo() != null
                && getDaysBetween(date, request.getAgreementDateTo()) < 0
                && getDaysBetween(date, request.getAgreementDateFrom()) < 0)
                ? Optional.of(new ValidationError("agreementDateFrom and agreementDateTo", "AgreementDateFrom and AgreementDateTo should only be from the future"))
                : Optional.empty();
    }

    private long getDaysBetween(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

}