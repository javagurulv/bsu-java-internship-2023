package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.Date;
import java.util.Optional;


public class ValidationHelper {

    protected Optional<ValidationError> validateMandatoryField(String fieldName, String field) {
        return (field == null || field.isEmpty()) ?
                Optional.of(new ValidationError(fieldName, "Shouldn't be empty!")) :
                Optional.empty();
    }

    protected Optional<ValidationError> validateMandatoryField(String fieldName, Date field) {
        return (field == null) ?
                Optional.of(new ValidationError(fieldName, "Shouldn't be empty!")) :
                Optional.empty();
    }

}
