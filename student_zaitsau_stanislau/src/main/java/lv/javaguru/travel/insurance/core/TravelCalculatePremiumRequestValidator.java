package lv.javaguru.travel.insurance.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

public class TravelCalculatePremiumRequestValidator {
    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();

        validateFirstName(request.getPersonFirstName()).ifPresent(errors::add);

        validateLastName(request.getPersonLastName()).ifPresent(errors::add);

        Optional<ValidationError> dateFromValidation = validateDateFrom(request.getAgreementDateFrom());

        Optional<ValidationError> dateToValidation = validateDateFrom(request.getAgreementDateTo());

        dateFromValidation.ifPresent(errors::add);

        dateToValidation.ifPresent(errors::add);

        if (dateToValidation.isEmpty() && dateFromValidation.isEmpty()) {
            validateDateFromAndDateTo(request.getAgreementDateFrom(), request.getAgreementDateTo()).ifPresent(errors::add);
        }

        return errors;
    }

    private Optional<ValidationError> validateFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty())
            return Optional.of(new ValidationError("First name should not be null or empty.", "firstName"));
        return Optional.empty();
    }

    private Optional<ValidationError> validateLastName(String lastName) {
        if (lastName == null || lastName.isEmpty())
            return Optional.of(new ValidationError("Last name should not be null or empty.", "last"));
        return Optional.empty();
    }

    private Optional<ValidationError> validateDateFrom(Date date) {
        if (date == null)
            return Optional.of(new ValidationError("Date from cannot be null.", "dateFrom"));
        return Optional.empty();
    }

    private Optional<ValidationError> validateDateTo(Date date) {
        if (date == null)
            return Optional.of(new ValidationError("Date to cannot be null.", "dateTo"));
        return Optional.empty();
    }

    private Optional<ValidationError> validateDateFromAndDateTo(Date dateFrom, Date dateTo) {
        if (dateFrom.compareTo(dateTo) > 0)
            return Optional.of(new ValidationError("Date from cannot be greater then date to.", "dateFrom and dateTo"));
        return Optional.empty();
    }
}
