package lv.javaguru.travel.insurance.core;

import lv.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        boolean flagForCheckDateFromLessTo = true;
        if(!validateAgreementDateFrom(request).isEmpty()){
            validateAgreementDateFrom(request).ifPresent(errors::add);
            flagForCheckDateFromLessTo = false;
        }
        if(!validateAgreementDateTo(request).isEmpty()){
            validateAgreementDateTo(request).ifPresent(errors::add);
            flagForCheckDateFromLessTo = false;
        }
        if(flagForCheckDateFromLessTo){
            validateAgreementDateFromLessTo(request).ifPresent(errors::add);
        }
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
        return (request.getAgreementDateFrom().after(request.getAgreementDateTo()))
                ? Optional.of(new ValidationError("agreementDateFrom", "agreementDateFrom must be less than agreementDateTo!"))
                : Optional.empty();
    }
}