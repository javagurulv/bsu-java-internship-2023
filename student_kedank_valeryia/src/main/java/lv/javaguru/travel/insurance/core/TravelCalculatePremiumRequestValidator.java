package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.rest.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest request){
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        validateDateFrom(request).ifPresent(errors::add);
        validateDateTo(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validatePersonFirstName(TravelCalculatePremiumRequest request){
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(new ValidationError("personFirstName", "Most not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validatePersonLastName(TravelCalculatePremiumRequest request){
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationError("personLastName", "Most not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateDateFrom(TravelCalculatePremiumRequest request){
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationError("dateFrom", "Most not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateDateTo(TravelCalculatePremiumRequest request){
        return (request.getAgreementDateTo() == null)
                ? Optional.of(new ValidationError("dateTo", "Most not be empty!"))
                : Optional.empty();
    }
}