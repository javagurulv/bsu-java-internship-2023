package lv.javaguru.travel.insurance.validation;

import lv.javaguru.travel.insurance.dto.ValidationError;

import java.util.Optional;

public interface RequestValidation<REQ> {
    public Optional<ValidationError> execute(REQ request);
}
