package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
class TravelCalculatePremiumRequestValidator {
    DateTimeService dateTimeService = new DateTimeService();

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        validateAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateTo(request).ifPresent(errors::add);
        validateAgreementDateFromLessTo(request).ifPresent(errors::add);
        validateDateFromOfFutureTime(request).ifPresent(errors::add);
        validateDateToOfFutureTime(request).ifPresent(errors::add);
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

    private Optional<ValidationError> validateAgreementDateFromLessTo(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();
        return (dateFrom != null && dateTo != null
                && (dateFrom.equals(dateTo) || dateFrom.after(dateTo)))
                ? Optional.of(new ValidationError("agreementDateFrom", "agreementDateFrom must be less than agreementDateTo!"))
                : Optional.empty();
    }
   private Optional<ValidationError> validateDateFromOfFutureTime(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        return (dateFrom != null && (dateTimeService.getCurrentDateTime().after(dateFrom)))
                ? Optional.of(new ValidationError("agreementDateFrom", "agreementDateFrom must be future date"))
                : Optional.empty();
    }
    private Optional<ValidationError> validateDateToOfFutureTime(TravelCalculatePremiumRequest request) {
        Date dateTo = request.getAgreementDateTo();
        return (dateTo != null && (dateTimeService.getCurrentDateTime().after(dateTo)))
                ? Optional.of(new ValidationError("agreementDateTo", "agreementDateTo must be future date"))
                : Optional.empty();
    }
}
