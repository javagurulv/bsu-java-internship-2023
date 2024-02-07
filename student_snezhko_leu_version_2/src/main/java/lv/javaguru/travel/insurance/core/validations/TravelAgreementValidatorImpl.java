package lv.javaguru.travel.insurance.core.validations;


//import lv.javaguru.travel.insurance.dto.ValidationError;

import lv.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class TravelAgreementValidatorImpl {       //will make default access mod
    @Autowired
    List<TravelAgreementFieldValidation> agreementValidations;

    @Autowired
    List<TravelPersonFieldValidation> personValidations;

    public List<ValidationErrorDTO> validate(AgreementDTO request) {
        List<ValidationErrorDTO> errors = new ArrayList<>();
            agreementValidations.forEach(validation -> {
                Optional<ValidationErrorDTO> error = null;
                error = validation.validate(request);
                if (!error.isEmpty()) {
                    errors.add(error.get());
                }
                List<ValidationErrorDTO> validList = validation.validateList(request);
                if (validList != null) {
                    errors.addAll(validList);
                }
            });

            if(request.getPersons() != null && !request.getPersons().isEmpty()) {
                request.getPersons().forEach(person -> {
                    personValidations.forEach(validation -> {
                        Optional<ValidationErrorDTO> error = validation.validate(person);
                        error.ifPresent(errors::add);
                        List<ValidationErrorDTO> validList = validation.validateList(person);
                        if (validList != null) {
                            errors.addAll(validList);
                        }
                    });
                });
            }

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