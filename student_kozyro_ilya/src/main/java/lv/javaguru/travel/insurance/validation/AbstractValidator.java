package lv.javaguru.travel.insurance.validation;

import lombok.AllArgsConstructor;
import lv.javaguru.travel.insurance.dto.CoreRequest;
import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
public abstract class AbstractValidator<REQ extends CoreRequest> {

    public abstract ArrayList<ValidationError> validate(REQ req);

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
