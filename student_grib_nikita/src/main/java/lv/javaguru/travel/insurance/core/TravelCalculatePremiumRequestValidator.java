package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
class TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();

        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        validateAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateTo(request).ifPresent(errors::add);
        validateDateFromLessThanDateTo(request).ifPresent(errors::add);
        validateDateToInFuture(request).ifPresent(errors::add);
        validateDateFromInFuture(request).ifPresent(errors::add);

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

    private Optional<ValidationError> validateDateFromLessThanDateTo(TravelCalculatePremiumRequest request) {
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date agreementDateTo = request.getAgreementDateTo();
        return (agreementDateTo != null
                && agreementDateFrom != null
                && (agreementDateFrom.after(agreementDateTo) || agreementDateFrom.equals(agreementDateTo)))  // тк в условии не говорится про равенство дат!
                ? Optional.of(new ValidationError("agreementDateFrom", "AgreementDateFrom should be less than agreementDateTo!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateDateToInFuture(TravelCalculatePremiumRequest request){
        Date agreementDateTo = request.getAgreementDateTo();
        Date curDate = new Date();
        DataTimeService timeService = new DataTimeService();

        return ((agreementDateTo != null) && timeService.getDaysBetween(curDate, agreementDateTo) < 0)
                ? Optional.of(new ValidationError("agreementDateTo", "A date must be in future!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateDateFromInFuture(TravelCalculatePremiumRequest request){
        Date agreementDateFrom = request.getAgreementDateFrom();
        Date curDate = new Date();
        DataTimeService timeService = new DataTimeService();

        return ((agreementDateFrom != null) && timeService.getDaysBetween(curDate, agreementDateFrom) < 0)
                ? Optional.of(new ValidationError("agreementDateFrom", "A date must be in future!"))
                : Optional.empty();
    }

}