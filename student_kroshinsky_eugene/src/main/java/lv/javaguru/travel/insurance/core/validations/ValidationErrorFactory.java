package lv.javaguru.travel.insurance.core.validations;

import lv.javaguru.travel.insurance.dto.ValidationError;

public interface ValidationErrorFactory {
    ValidationError createValidationError(String errorCode);
}
