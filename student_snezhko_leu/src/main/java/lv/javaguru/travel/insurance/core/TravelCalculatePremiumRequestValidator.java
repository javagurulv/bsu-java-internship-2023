package lv.javaguru.travel.insurance.core;


//import lv.javaguru.travel.insurance.dto.ValidationError;
import lv.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.rest.TravelRequestValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class TravelCalculatePremiumRequestValidator {       //will make default access mod
    @Autowired
    List<TravelRequestValidation> validations;
    public List<ValidationError> validate(TravelCalculatePremiumRequestV1 request) {
  //      List<TravelRequestValidation> validations = new ArrayList<>();
        List<ValidationError> errors = new ArrayList<>();
        /*
        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        validateAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateTo(request).ifPresent(errors::add);
        */
/*
        validations.add(new TravelRequestFirstNameValidation());
        validations.add(new TravelRequestLastNameValidation());
        validations.add(new TravelRequestDateFromValidation());
        validations.add(new TravelRequestDateToValidation());
        validations.add(new TravelRequestWithoutRisksValidation());
*/
            validations.forEach(validation -> {
                Optional<ValidationError> error = null;
                error = validation.validate(request);
                if (!error.isEmpty()) {
                    errors.add(error.get());
                }
                List<ValidationError> validList = validation.validateList(request);
                if (validList != null) {
                    errors.addAll(validList);
                }
            });
        return errors;
    }

    /*
    private Optional<ValidationError> validatePersonFirstName(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(new ValidationError("personFirstName", "Must not be empty!"))
                : Optional.empty();
    }
     */
    /*
    private Optional<ValidationError> validatePersonLastName(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationError("personLastName", "Must not be empty!"))
                : Optional.empty();
    }
    */
    /*
    private Optional<ValidationError> validateAgreementDateFrom(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateFrom() == null) {
            return Optional.of(new ValidationError("agreementDateFrom", "Must not be null!"));
        }
        else if (request.getAgreementDateFrom().compareTo(new Date()) <= 0) {
            return Optional.of(new ValidationError("agreementDateFrom", "Must not be in the past!"));
        }
        return Optional.empty();
    }
    */
    /*
    private Optional<ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest request) {
        if (request.getAgreementDateTo() == null) {
            return Optional.of(new ValidationError("agreementDateTo", "Must not be null!"));
        }
        else if (request.getAgreementDateTo().before(request.getAgreementDateFrom())) {
            return Optional.of(new ValidationError("agreementDateTo", "Must not be before agreementDateFrom!!!"));
        }
        return Optional.empty();
    }
     */
}