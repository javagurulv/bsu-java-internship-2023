package lv.javaguru.travel.insurance.validators;

import lv.javaguru.travel.insurance.core.DateTimeService;
import lv.javaguru.travel.insurance.core.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class TravelCalculatePremiumRequestValidator {

    @Autowired
    DateTimeService dateTimeService = new DateTimeService();

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
        return (request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null &&
                !request.getAgreementDateFrom().before(request.getAgreementDateTo()))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be less than agreementDateTo!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateDateFromInFuture(TravelCalculatePremiumRequest request) {
        Date currentDate = dateTimeService.getCurrentDate();
        return (request.getAgreementDateFrom() != null && request.getAgreementDateFrom().before(currentDate))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be in the future!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateDateToInFuture(TravelCalculatePremiumRequest request) {
        Date currentDate = dateTimeService.getCurrentDate();
        return (request.getAgreementDateTo() != null && request.getAgreementDateTo().before(currentDate))
                ? Optional.of(new ValidationError("agreementDateTo", "Must be in the future!"))
                : Optional.empty();
    }

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        validateAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateTo(request).ifPresent(errors::add);
        validateDateFromLessThenDateTo(request).ifPresent(errors::add);
        validateDateFromInFuture(request).ifPresent(errors::add);
        validateDateToInFuture(request).ifPresent(errors::add);
        return errors;
    }
}
